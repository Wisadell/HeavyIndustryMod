package heavyindustry.gen;

import arc.func.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import arc.util.pooling.*;
import heavyindustry.type.unit.*;
import heavyindustry.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.EntityCollisions.*;
import mindustry.entities.abilities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.input.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static mindustry.Vars.*;

public class MillipedeLegsUnit extends LegsUnit implements Millipedec {
    protected static Unit last;

    public int childId = -1, headId = -1;

    protected transient Unit head, parent, child, tail;
    protected transient float layer = 0f, scanTime = 0f;
    protected transient byte weaponIdx = 0;
    protected transient boolean removing = false, saveAdd = false;

    protected float splitHealthDiv = 1f;
    protected float regenTime = 0f;
    protected float waitTime = 0f;

    @Override
    public int classId() {
        return EntityRegister.getId(MillipedeLegsUnit.class);
    }

    @Override
    public void add() {
        if (added) return;
        index__all = Groups.all.addIndex(this);
        index__unit = Groups.unit.addIndex(this);
        index__sync = Groups.sync.addIndex(this);
        index__draw = Groups.draw.addIndex(this);

        added = true;

        updateLastPosition();

        resetLegs();

        team.data().updateCount(type, 1);

        //check if over unit cap
        if (type.useUnitCap && count() > cap() && !spawnedByCore && !dead && !state.rules.editor) {
            Call.unitCapDeath(this);
            team.data().updateCount(type, -1);
        }

        MillipedeUnitType uType = (MillipedeUnitType) type;
        if (isHead()) {
            if (saveAdd) {
                var seg = (Unit & Millipedec) child;
                while (seg != null) {
                    seg.add();
                    seg.setupWeapons(uType);
                    seg = (Unit & Millipedec) seg.child();
                }
                saveAdd = false;
                return;
            } else {
                ((Millipedec) addTail()).addTail();
            }
            float[] rot = {rotation() + uType.angleLimit};
            Tmp.v1.trns(rot[0] + 180f, uType.segmentOffset + uType.headOffset).add(this);
            distributeActionBack(u -> {
                if (u != this) {
                    u.x = Tmp.v1.x;
                    u.y = Tmp.v1.y;
                    u.rotation = rot[0];

                    rot[0] += uType.angleLimit;
                    Tmp.v2.trns(rot[0] + 180f, uType.segmentOffset);
                    Tmp.v1.add(Tmp.v2);

                    u.add();
                    u.setupWeapons(uType);
                }
            });
        }
    }

    @Override
    public Unit addTail() {
        if (isHead()) head = this;
        if (!isTail()) return null;
        Unit tail = type.constructor.get();
        tail.team = team;
        tail.setType(type);
        tail.ammo = type.ammoCapacity;
        tail.elevation = type.flying ? 1f : 0;
        tail.heal();

        MillipedeUnitType uType = (MillipedeUnitType) type;
        if (tail instanceof Millipedec) {
            float z = layer + uType.segmentLayerOffset;
            Tmp.v1.trns(rotation() + 180f, uType.segmentOffset).add(this);
            tail.set(Tmp.v1);
            ((Millipedec) tail).layer(z);
            ((Millipedec) tail).head(head);
            ((Millipedec) tail).parent(this);
            child = tail;
            tail.setupWeapons(uType);
            tail.add();
            ((Millipedec) tail).distributeActionForward(u -> u.setupWeapons(uType));
        }
        return tail;
    }

    @Override
    public void afterSync() {
        super.afterSync();
        if (headId != -1 && head == null) {
            Unit h = Groups.unit.getByID(headId);
            if (h instanceof Millipedec) {
                head = h;
                headId = -1;
            }
        }
        if (childId != -1 && child == null) {
            Unit c = Groups.unit.getByID(childId);
            if (c instanceof Millipedec wc) {
                child = c;
                wc.parent(this);
                childId = -1;
            }
        }
    }

    @Override
    public void aim(float x, float y) {
        if (isHead()) distributeActionBack(u -> {
            Tmp.v1.set(x, y).sub(this.x, this.y);
            if (Tmp.v1.len() < type.aimDst) Tmp.v1.setLength(type.aimDst);
            float
                    tx = Tmp.v1.x + this.x,
                    ty = Tmp.v1.y + this.y;

            for (WeaponMount mount : u.mounts)
                if (mount.weapon.controllable) {
                    mount.aimX = tx;
                    mount.aimY = ty;
                }

            u.aimX = tx;
            u.aimY = ty;
        });
    }

    @Override
    public int cap() {
        int max = Math.max(((MillipedeUnitType) type).maxSegments, ((MillipedeUnitType) type).segmentLength);
        return Math.max(Units.getCap(team), Units.getCap(team) * max);
    }

    //TODO WHY DOES IT NOT SHOW UP ON THE UNIT CODE
    /*boolean canJoin(Millipedec other) {
        MillipedeUnitType uType = (MillipedeUnitType) type;

        return uType == other.type() && other.countAll() + countAll() <= uType.maxSegments;
    }*/

    // TODO make private
    public void connect(Millipedec other) {
        if (isHead() && other.isTail()) {
            MillipedeUnitType uType = (MillipedeUnitType) type;
            float z = other.layer() + uType.segmentLayerOffset;
            distributeActionBack(u -> {
                u.layer(z);
                u.head(other.head());
            });
            other.child(this);
            parent = (Unit) other;
            head = other.head();
            setupWeapons(type);
            uType.chainSound.at(this);
            if (controller() instanceof Player) {
                UnitController con = controller();
                other.head().controller(con);
                con.unit(other.head());
                controller(type.createController(this));
            }
        }
    }

    @Override
    public void controller(UnitController next) {
        if (next instanceof Player && head != null && !isHead()) {
            head.controller(next);
            return;
        }
        super.controller(next);
    }

    @Override
    public void controlWeapons(boolean rotate, boolean shoot) {
        if (isHead()) distributeActionBack((unit) -> {
            for (WeaponMount mount : unit.mounts) {
                if (mount.weapon.controllable) {
                    mount.rotate = rotate;
                    mount.shoot = shoot;
                }
            }

            unit.isShooting = shoot;
        });
    }

    /** counts the number of units towards the tail. */
    @Override
    public int countBackward() {
        Millipedec current = this;
        int num = 0;
        while (current != null && current.child() != null) {
            if (current.child() instanceof Millipedec) {
                num++;
                current = (Millipedec) current.child();
            } else {
                current = null;
            }
        }
        return num;
    }

    /** counts the number of units in this snake, including itself. */
    @Override
    public int countAll() {
        return countBackward() + countForward() + 1;
    }

    /** counts the number of units towards the head. */
    @Override
    public int countForward() {
        Millipedec current = this;
        int num = 0;
        while (current != null && current.parent() != null) {
            if (current.parent() instanceof Millipedec) {
                num++;
                current = (Millipedec) current.parent();
            } else {
                current = null;
            }
        }
        return num;
    }

    @Override
    public void damage(float amount) {
        if (!isHead() && head != null && !((MillipedeUnitType) type).splittable) {
            head.damage(amount);
            return;
        }
        super.damage(amount);
    }

    /** runs a consumer with every unit towards the tail. */
    @Override
    public <T extends Unit & Millipedec> void distributeActionBack(Cons<T> cons) {
        T current = as();
        cons.get(current);
        while (current.child() != null) {
            cons.get(current.child().as());
            current = current.child().as();
        }
    }

    /** runs a consumer with every unit towards the head. */
    @Override
    public <T extends Unit & Millipedec> void distributeActionForward(Cons<T> cons) {
        T current = as();
        cons.get(current);
        while (current.parent() != null) {
            cons.get(current.parent().as());
            current = current.parent().as();
        }
    }

    @Override
    public void heal(float amount) {
        if (!isHead() && head != null && !((MillipedeUnitType) type).splittable) {
            head.heal(amount);
            return;
        }
        super.heal(amount);
    }

    @Override
    public TextureRegion icon() {
        MillipedeUnitType uType = (MillipedeUnitType) type;

        if (isHead() && isTail()) return type.fullIcon;
        if (isTail()) return uType.tailOutline;
        if (!isHead()) return uType.segmentOutline;
        return type.fullIcon;
    }

    @Override
    public boolean isHead() {
        return parent == null || head == this;
    }

    @Override
    public boolean isSegment() {
        return !isHead() && !isTail();
    }

    @Override
    public boolean isTail() {
        return child == null;
    }

    @Override
    public boolean isAI() {
        if (head != null && !isHead()) return head.isAI();
        return controller() instanceof AIController;
    }

    @Override
    public void remove() {
        if (!added) return;
        Groups.unit.remove(this);
        Groups.draw.remove(this);
        Groups.sync.remove(this);
        Groups.all.remove(this);

        added = false;

        //notify client of removal
        if (Vars.net.client()) {
            Vars.netClient.addRemovedEntity(id());
        }

        team.data().updateCount(type, -1);
        controller.removed(this);

        //make sure trail doesn't just go poof
        if (trail != null && trail.size() > 0) {
            Fx.trailFade.at(x, y, trail.width(), type.trailColor == null ? team.color : type.trailColor, trail.copy());
        }

        for (WeaponMount mount : mounts) {
            if (mount.weapon.continuous && mount.bullet != null && mount.bullet.owner == this) {
                mount.bullet.time = mount.bullet.lifetime - 10f;
                mount.bullet = null;
            }

            if (mount.sound != null) {
                mount.sound.stop();
            }
        }

        MillipedeUnitType uType = (MillipedeUnitType) type;
        if (uType.splittable) {
            if (child != null && parent != null) uType.splitSound.at(x(), y());
            if (child != null) {
                var wc = (Unit & Millipedec) child;
                float z = 0f;
                wc.parent(null);
                wc.distributeActionBack(u -> u.setupWeapons(uType));
                while (wc != null) {
                    wc.layer(z += uType.segmentLayerOffset);
                    wc.splitHealthDiv(wc.splitHealthDiv() * 2f);
                    wc.head(child);
                    if (wc.isTail()) wc.waitTime(5f * 60f);
                    wc = (Unit & Millipedec) wc.child();
                }
            }
            if (parent != null) {
                Millipedec wp = ((Millipedec) parent);
                distributeActionForward(u -> {
                    u.setupWeapons(uType);
                    if (u != this) {
                        u.splitHealthDiv(u.splitHealthDiv() * 2f);
                    }
                });
                wp.child(null);
                wp.waitTime(5f * 60f);
            }
            parent = null;
            child = null;
        }
        if (!isHead() && !uType.splittable && !removing) {
            head.remove();
            return;
        }
        if (isHead() && !uType.splittable) {
            distributeActionBack(u -> {
                if (u != this) {
                    u.removing(true);
                    u.remove();
                    u.removing(false);
                }
            });
        }
        parent = null;
        child = null;
    }

    @Override
    public void resetLegs(float legLength) {
        MillipedeUnitType uType = (MillipedeUnitType) type;
        int count = 0;
        if ((isHead() && isTail()) || isSegment()) {
            count = uType.segmentLegCount;
        } else {
            if (isHead()) count = uType.headLegCount;
            if (isTail()) count = uType.tailLegCount;
        }

        if (legs.length == count) return;

        legs = new Leg[count];
        if (type.lockLegBase) {
            baseRotation = rotation;
        }

        for (int i = 0; i < legs.length; ++i) {
            Leg l = new Leg();
            float dstRot = this.legAngle(i);
            Vec2 baseOffset = this.legOffset(Tmp.v5, i).add(this.x, this.y);
            l.joint.trns(dstRot, legLength / 2.0F).add(baseOffset);
            l.base.trns(dstRot, legLength).add(baseOffset);
            legs[i] = l;
        }

    }

    /** only heads will be written */
    @Override
    public boolean serialize() {
        return isHead();
    }

    @Override
    public void setupWeapons(UnitType def) {
        MillipedeUnitType uType = (MillipedeUnitType) def;
        if ((isTail() && uType.tailHasWeapon) || (isHead() && uType.headHasWeapon) || isSegment()) {
            Seq<Weapon> seq = uType.segmentWeapons[Math.min(uType.segmentWeapons.length - 1, countForward())];
            mounts = new WeaponMount[seq.size];
            for (int i = 0; i < mounts.length; i++) {
                mounts[i] = seq.get(i).mountType.get(seq.get(i));
            }
        } else {
            mounts = new WeaponMount[]{};
        }
    }

    @Override
    public SolidPred solidity() {
        if (!isHead()) return null;

        return type.allowLegStep ? EntityCollisions::legsSolid : EntityCollisions::solid;
    }

    @Override
    public boolean moving() {
        if (!isHead()) return head.moving();
        return !vel().isZero(0.01f);
    }

    @Override
    public float speed() {
        if (!isHead()) return 0f;
        float strafePenalty = isGrounded() || !isPlayer() ? 1f : Mathf.lerp(1f, type.strafePenalty, Angles.angleDist(vel().angle(), rotation) / 180f);
        float boost = Mathf.lerp(1f, type.canBoost ? type.boostMultiplier : 1f, elevation);
        return type.speed * strafePenalty * boost * floorSpeedMultiplier();
    }

    @Override
    public void update() {
        updateVel();
        updateBounded();
        updateBuildLogic();
        updateFlying();
        updateHealth();
        updateItems();
        updateMillipede();
        updateLegs();
        updateMiner();
        updateShield();
        updateStatus();
        updateHealthDiv();
        updateSync();
        updateUnit();
        updateWeapons();
        updatePost();
    }

    public void updateVel() {
        //do not update velocity on the client at all, unless it's non-interpolated
        //velocity conflicts with interpolation.
        if (!net.client() || isLocal()) {
            float px = x, py = y;
            move(vel.x * Time.delta, vel.y * Time.delta);
            if (Mathf.equal(px, x)) vel.x = 0;
            if (Mathf.equal(py, y)) vel.y = 0;

            vel.scl(Math.max(1f - drag * Time.delta, 0));
        }
    }

    public void updateBounded() {
        if (!isHead() && !type.bounded) return;

        float bot = 0f, left = 0f, top = world.unitHeight(), right = world.unitWidth();

        //TODO hidden map rules only apply to player teams? should they?
        if (state.rules.limitMapArea && !team.isAI()) {
            bot = state.rules.limitY * tilesize;
            left = state.rules.limitX * tilesize;
            top = state.rules.limitHeight * tilesize + bot;
            right = state.rules.limitWidth * tilesize + left;
        }

        if (!net.client() || isLocal()) {
            float dx = 0f, dy = 0f;

            //repel unit out of bounds
            if (x < left) dx += (-(x - left) / warpDst);
            if (y < bot) dy += (-(y - bot) / warpDst);
            if (x > right) dx -= (x - right) / warpDst;
            if (y > top) dy -= (y - top) / warpDst;

            velAddNet(dx * Time.delta, dy * Time.delta);
        }

        //clamp position if not flying
        if (isGrounded()) {
            x = Mathf.clamp(x, left, right - tilesize);
            y = Mathf.clamp(y, bot, top - tilesize);
        }

        //kill when out of bounds
        if (x < -finalWorldBounds + left || y < -finalWorldBounds + bot || x >= right + finalWorldBounds || y >= top + finalWorldBounds) {
            kill();
        }
    }

    public void updateFlying() {
        Floor floor = floorOn();

        if (isFlying() != wasFlying) {
            if (wasFlying) {
                if (tileOn() != null) {
                    Fx.unitLand.at(x, y, floorOn().isLiquid ? 1f : 0.5f, tileOn().floor().mapColor);
                }
            }

            wasFlying = isFlying();
        }

        if (!hovering && isGrounded()) {
            if ((splashTimer += Mathf.dst(deltaX(), deltaY())) >= (7f + hitSize() / 8f)) {
                floor.walkEffect.at(x, y, hitSize() / 8f, floor.mapColor);
                splashTimer = 0f;

                if (emitWalkSound()) {
                    floor.walkSound.at(x, y, Mathf.random(floor.walkSoundPitchMin, floor.walkSoundPitchMax), floor.walkSoundVolume);
                }
            }
        }

        updateDrowning();
    }

    public void updateHealth() {
        hitTime -= Time.delta / hitDuration;
    }

    public void updateItems() {
        stack.amount = Mathf.clamp(stack.amount, 0, itemCapacity());
        itemTime = Mathf.lerpDelta(itemTime, Mathf.num(hasItem()), 0.05f);
    }

    public void updateLegs() {
        if (Mathf.dst(deltaX(), deltaY()) > 0.001f) {
            baseRotation = Angles.moveToward(baseRotation, Mathf.angle(deltaX(), deltaY()), type.rotateSpeed);
        }

        if (type.lockLegBase) {
            baseRotation = rotation;
        }

        float legLength = type.legLength;

        //set up initial leg positions
        if (legs.length != type.legCount) {
            resetLegs();
        }

        float moveSpeed = type.legSpeed;
        int div = Math.max(legs.length / type.legGroupSize, 2);
        moveSpace = legLength / 1.6f / (div / 2f) * type.legMoveSpace;
        //TODO should move legs even when still, based on speed. also, to prevent "slipping", make sure legs move when they are too far from their destination
        totalLength += type.legContinuousMove ? type.speed * speedMultiplier * Time.delta : Mathf.dst(deltaX(), deltaY());

        float trns = moveSpace * 0.85f * type.legForwardScl;

        //rotation + offset vector
        boolean moving = moving();
        Vec2 moveOffset = !moving ? Tmp.v4.setZero() : Tmp.v4.trns(Angles.angle(deltaX(), deltaY()), trns);
        //make it smooth, not jumpy
        moveOffset = curMoveOffset.lerpDelta(moveOffset, 0.1f);

        lastDeepFloor = null;
        int deeps = 0;

        for (int i = 0; i < legs.length; i++) {
            float dstRot = legAngle(i);
            Vec2 baseOffset = legOffset(Tmp.v5, i).add(x, y);
            Leg l = legs[i];

            //TODO is limiting twice necessary?
            l.joint.sub(baseOffset).clampLength(type.legMinLength * legLength / 2f, type.legMaxLength * legLength / 2f).add(baseOffset);
            l.base.sub(baseOffset).clampLength(type.legMinLength * legLength, type.legMaxLength * legLength).add(baseOffset);

            float stageF = (totalLength + i * type.legPairOffset) / moveSpace;
            int stage = (int) stageF;
            int group = stage % div;
            boolean move = i % div == group;
            boolean side = i < legs.length / 2;
            //back legs have reversed directions
            boolean backLeg = Math.abs((i + 0.5f) - legs.length / 2f) <= 0.501f;
            if (backLeg && type.flipBackLegs) side = !side;
            if (type.flipLegSide) side = !side;

            l.moving = move;
            l.stage = moving ? stageF % 1f : Mathf.lerpDelta(l.stage, 0f, 0.1f);

            Floor floor = Vars.world.floorWorld(l.base.x, l.base.y);
            if (floor.isDeep()) {
                deeps++;
                lastDeepFloor = floor;
            }

            if (l.group != group) {

                //create effect when transitioning to a group it can't move in
                if (!move && (moving || !type.legContinuousMove) && i % div == l.group) {
                    if (!headless && !inFogTo(player.team())) {
                        if (floor.isLiquid) {
                            floor.walkEffect.at(l.base.x, l.base.y, type.rippleScale, floor.mapColor);
                            floor.walkSound.at(x, y, 1f, floor.walkSoundVolume);
                        } else {
                            Fx.unitLandSmall.at(l.base.x, l.base.y, type.rippleScale, floor.mapColor);
                        }

                        //shake when legs contact ground
                        if (type.stepShake > 0) {
                            Effect.shake(type.stepShake, type.stepShake, l.base);
                        }
                    }

                    if (type.legSplashDamage > 0 && !disarmed) {
                        Damage.damage(team, l.base.x, l.base.y, type.legSplashRange, type.legSplashDamage * state.rules.unitDamage(team), false, true);
                    }
                }

                l.group = group;
            }

            //leg destination
            Vec2 legDest = Tmp.v1.trns(dstRot, legLength * type.legLengthScl).add(baseOffset).add(moveOffset);
            //join destination
            Vec2 jointDest = Tmp.v2;
            InverseKinematics.solve(legLength / 2f, legLength / 2f, Tmp.v6.set(l.base).sub(baseOffset), side, jointDest);
            jointDest.add(baseOffset);
            Tmp.v6.set(baseOffset).lerp(l.base, 0.5f);

            if (move) {
                float moveFract = stageF % 1f;

                l.base.lerpDelta(legDest, moveFract);
                l.joint.lerpDelta(jointDest, moveFract / 2f);
            }

            l.joint.lerpDelta(jointDest, moveSpeed / 4f);

            //limit again after updating
            l.joint.sub(baseOffset).clampLength(type.legMinLength * legLength / 2f, type.legMaxLength * legLength / 2f).add(baseOffset);
            l.base.sub(baseOffset).clampLength(type.legMinLength * legLength, type.legMaxLength * legLength).add(baseOffset);
        }

        //when at least 1 leg is touching land, it can't drown
        if (deeps != legs.length || !floorOn().isDeep()) {
            lastDeepFloor = null;
        }
    }

    public void updateMillipede() {
        MillipedeUnitType uType = (MillipedeUnitType) type;
        if (countAll() < 3) kill();
        if (uType.splittable && isTail() && uType.regenTime > 0f) {
            int forward = countForward();
            if (forward < uType.segmentLength - 1) {
                regenTime += Time.delta;
                if (regenTime >= uType.regenTime) {
                    regenTime = 0f;
                    Unit unit;
                    if ((unit = addTail()) != null) {
                        health /= 2f;
                        unit.health = health;
                        ((MillipedeUnitType) type).chainSound.at(this);
                    }
                }
            }
        } else regenTime = 0f;
        if (isTail() && waitTime > 0) waitTime -= Time.delta;
        if (!uType.splittable) {
            if (!isHead()) health = head.health;
            if ((isHead() && isAdded()) || (head != null && head.isAdded())) {
                Millipedec t = (Millipedec) child;
                while (t != null && !t.isAdded()) {
                    t.add();
                    t = (Millipedec) t.child();
                }
            }
        }
        if (uType.splittable && (parent != null || child != null) && dead) {
            destroy();
        }
    }

    public void updateMiner() {
        if (mineTile == null) return;

        Building core = closestCore();
        Item item = getMineResult(mineTile);

        if (core != null && item != null && !acceptsItem(item) && within(core, mineTransferRange) && !offloadImmediately()) {
            int accepted = core.acceptStack(item(), stack().amount, this);
            if (accepted > 0) {
                Call.transferItemTo(this, item(), accepted,
                        mineTile.worldx() + Mathf.range(tilesize / 2f),
                        mineTile.worldy() + Mathf.range(tilesize / 2f), core);
                clearItem();
            }
        }

        if ((!net.client() || isLocal()) && !validMine(mineTile)) {
            mineTile = null;
            mineTimer = 0f;
        } else if (mining() && item != null) {
            mineTimer += Time.delta * type.mineSpeed;

            if (Mathf.chance(0.06 * Time.delta)) {
                Fx.pulverizeSmall.at(mineTile.worldx() + Mathf.range(tilesize / 2f), mineTile.worldy() + Mathf.range(tilesize / 2f), 0f, item.color);
            }

            if (mineTimer >= 50f + (type.mineHardnessScaling ? item.hardness * 15f : 15f)) {
                mineTimer = 0;

                if (state.rules.sector != null && team() == state.rules.defaultTeam)
                    state.rules.sector.info.handleProduction(item, 1);

                if (core != null && within(core, mineTransferRange) && core.acceptStack(item, 1, this) == 1 && offloadImmediately()) {
                    //add item to inventory before it is transferred
                    if (item() == item && !net.client()) addItem(item);
                    Call.transferItemTo(this, item, 1,
                            mineTile.worldx() + Mathf.range(tilesize / 2f),
                            mineTile.worldy() + Mathf.range(tilesize / 2f), core);
                } else if (acceptsItem(item)) {
                    //this is clientside, since items are synced anyway
                    InputHandler.transferItemToUnit(item,
                            mineTile.worldx() + Mathf.range(tilesize / 2f),
                            mineTile.worldy() + Mathf.range(tilesize / 2f),
                            this);
                } else {
                    mineTile = null;
                    mineTimer = 0f;
                }
            }

            if (!headless) {
                control.sound.loop(type.mineSound, this, type.mineSoundVolume);
            }
        }
    }

    public void updateShield() {
        shieldAlpha -= Time.delta / 15f;
        if (shieldAlpha < 0) shieldAlpha = 0f;
    }

    public void updateStatus() {
        Floor floor = floorOn();
        if (isGrounded() && !type.hovering) {
            //apply effect
            apply(floor.status, floor.statusDuration);
        }

        applied.clear();
        armorOverride = -1f;
        speedMultiplier = damageMultiplier = healthMultiplier = reloadMultiplier = buildSpeedMultiplier = dragMultiplier = 1f;
        disarmed = false;

        if (statuses.isEmpty()) return;

        int index = 0;

        while (index < statuses.size) {
            StatusEntry entry = statuses.get(index++);

            entry.time = Math.max(entry.time - Time.delta, 0);

            if (entry.effect == null || (entry.time <= 0 && !entry.effect.permanent)) {
                if (entry.effect != null) {
                    entry.effect.onRemoved(this);
                }

                Pools.free(entry);
                index--;
                statuses.remove(index);
            } else {
                applied.set(entry.effect.id);

                //TODO this is very ugly...
                if (entry.effect.dynamic) {
                    speedMultiplier *= entry.speedMultiplier;
                    healthMultiplier *= entry.healthMultiplier;
                    damageMultiplier *= entry.damageMultiplier;
                    reloadMultiplier *= entry.reloadMultiplier;
                    buildSpeedMultiplier *= entry.buildSpeedMultiplier;
                    dragMultiplier *= entry.dragMultiplier;
                    //armor is a special case; many units have it set it to 0, so an override at values >= 0 is used
                    if (entry.armorOverride >= 0f) armorOverride = entry.armorOverride;
                } else {
                    speedMultiplier *= entry.effect.speedMultiplier;
                    healthMultiplier *= entry.effect.healthMultiplier;
                    damageMultiplier *= entry.effect.damageMultiplier;
                    reloadMultiplier *= entry.effect.reloadMultiplier;
                    buildSpeedMultiplier *= entry.effect.buildSpeedMultiplier;
                    dragMultiplier *= entry.effect.dragMultiplier;
                }

                disarmed |= entry.effect.disarm;

                entry.effect.update(this, entry.time);
            }
        }
    }

    public void updateSync() {
        //interpolate the player if:
        //- this is a client and the entity is everything except the local player
        //- this is a server and the entity is a remote player
        if ((Vars.net.client() && !isLocal()) || isRemote()) {
            interpolate();
        }
    }

    public void updateUnit() {
        type.update(this);

        if (wasHealed && healTime <= -1f) {
            healTime = 1f;
        }
        healTime -= Time.delta / 20f;
        wasHealed = false;

        //die on captured sectors immediately
        if (team.isOnlyAI() && state.isCampaign() && state.getSector().isCaptured()) {
            kill();
        }

        if (!headless && type.loopSound != Sounds.none) {
            control.sound.loop(type.loopSound, this, type.loopSoundVolume);
        }

        //check if environment is unsupported
        if (!type.supportsEnv(state.rules.env) && !dead) {
            Call.unitEnvDeath(this);
            team.data().updateCount(type, -1);
        }

        if (state.rules.unitAmmo && ammo < type.ammoCapacity - 0.0001f) {
            resupplyTime += Time.delta;

            //resupply only at a fixed interval to prevent lag
            if (resupplyTime > 10f) {
                type.ammoType.resupply(this);
                resupplyTime = 0f;
            }
        }

        for (Ability a : abilities) {
            a.update(this);
        }

        if (trail != null) {
            trail.length = type.trailLength;

            float scale = type.useEngineElevation ? elevation : 1f;
            float offset = type.engineOffset / 2f + type.engineOffset / 2f * scale;

            float cx = x + Angles.trnsx(rotation + 180, offset), cy = y + Angles.trnsy(rotation + 180, offset);
            trail.update(cx, cy);
        }

        drag = type.drag * (isGrounded() ? (floorOn().dragMultiplier) : 1f) * dragMultiplier * state.rules.dragMultiplier;

        //apply knockback based on spawns
        if (team != state.rules.waveTeam && state.hasSpawns() && (!net.client() || isLocal()) && hittable()) {
            float relativeSize = state.rules.dropZoneRadius + hitSize / 2f + 1f;
            for (Tile spawn : spawner.getSpawns()) {
                if (within(spawn.worldx(), spawn.worldy(), relativeSize)) {
                    velAddNet(Tmp.v1.set(this).sub(spawn.worldx(), spawn.worldy()).setLength(0.1f + 1f - dst(spawn) / relativeSize).scl(0.45f * Time.delta));
                }
            }
        }

        //simulate falling down
        if (dead || health <= 0) {
            //less drag when dead
            drag = 0.01f;

            //standard fall smoke
            if (Mathf.chanceDelta(0.1)) {
                Tmp.v1.rnd(Mathf.range(hitSize));
                type.fallEffect.at(x + Tmp.v1.x, y + Tmp.v1.y);
            }

            //thruster fall trail
            if (Mathf.chanceDelta(0.2)) {
                float offset = type.engineOffset / 2f + type.engineOffset / 2f * elevation;
                float range = Mathf.range(type.engineSize);
                type.fallEngineEffect.at(
                        x + Angles.trnsx(rotation + 180, offset) + Mathf.range(range),
                        y + Angles.trnsy(rotation + 180, offset) + Mathf.range(range),
                        Mathf.random()
                );
            }

            //move down
            elevation -= type.fallSpeed * Time.delta;

            if (isGrounded() || health <= -maxHealth) {
                Call.unitDestroy(id);
            }
        }

        Tile tile = tileOn();
        Floor floor = floorOn();

        if (tile != null && isGrounded() && !type.hovering) {
            //unit block update
            if (tile.build != null) {
                tile.build.unitOn(this);
            }

            //apply damage
            if (floor.damageTaken > 0f) {
                damageContinuous(floor.damageTaken);
            }
        }

        //kill entities on tiles that are solid to them
        if (tile != null && !canPassOn()) {
            //boost if possible
            if (type.canBoost) {
                elevation = 1f;
            } else if (!net.client()) {
                kill();
            }
        }

        //AI only updates on the server
        if (!net.client() && !dead) {
            controller.updateUnit();
        }

        //clear controller when it becomes invalid
        if (!controller.isValidController()) {
            resetController();
        }

        //remove units spawned by the core
        if (spawnedByCore && !isPlayer() && !dead) {
            Call.unitDespawn(this);
        }
    }

    /** Update shooting and rotation for this unit. */
    public void updateWeapons() {
        for (WeaponMount mount : mounts) {
            mount.weapon.update(this, mount);
        }
    }

    protected void updateHealthDiv() {
        healthMultiplier /= splitHealthDiv;
    }

    protected void updatePost() {
        if (isHead()) {
            MillipedeUnitType uType = (MillipedeUnitType) type;
            last = this;
            distributeActionBack(u -> {
                if (u == this) return;

                u.aim(aimX(), aimY());

                float offset = this == last ? uType.headOffset : 0f;
                Tmp.v1.trns(last.rotation + 180f, (uType.segmentOffset / 2f) + offset).add(last);

                float rdx = u.deltaX - last.deltaX;
                float rdy = u.deltaY - last.deltaY;

                float angTo = !uType.preventDrifting || (last.deltaLen() > 0.001f && (rdx * rdx) + (rdy * rdy) > 0.00001f) ? u.angleTo(Tmp.v1) : u.rotation;

                u.rotation = angTo - (Utils.angleDistSigned(angTo, last.rotation, uType.angleLimit) * (1f - uType.anglePhysicsSmooth));
                u.trns(Tmp.v3.trns(u.rotation, last.deltaLen()));
                Tmp.v2.trns(u.rotation, uType.segmentOffset / 2f).add(u);

                Tmp.v2.sub(Tmp.v1).scl(Mathf.clamp(uType.jointStrength * Time.delta));

                Unit n = u;
                int cast = uType.segmentCast;
                while (cast > 0 && n != null) {
                    float scl = cast / (float) uType.segmentCast;
                    n.set(n.x - (Tmp.v2.x * scl), n.y - (Tmp.v2.y * scl));
                    n.updateLastPosition();
                    n = ((Millipedec) n).child();
                    cast--;
                }

                float nextHealth = (last.health() + u.health()) / 2f;
                if (!Mathf.equal(nextHealth, last.health(), 0.0001f))
                    last.health(Mathf.lerpDelta(last.health(), nextHealth, uType.healthDistribution));
                if (!Mathf.equal(nextHealth, u.health(), 0.0001f))
                    u.health(Mathf.lerpDelta(u.health(), nextHealth, uType.healthDistribution));

                Millipedec wrm = ((Millipedec) last);
                float nextHealthDv = (wrm.splitHealthDiv() + u.splitHealthDiv()) / 2f;
                if (!Mathf.equal(nextHealth, wrm.splitHealthDiv(), 0.0001f))
                    wrm.splitHealthDiv(Mathf.lerpDelta(wrm.splitHealthDiv(), nextHealthDv, uType.healthDistribution));
                if (!Mathf.equal(nextHealth, u.splitHealthDiv(), 0.0001f))
                    u.splitHealthDiv(Mathf.lerpDelta(u.splitHealthDiv(), nextHealthDv, uType.healthDistribution));
                last = u;
            });
            scanTime += Time.delta;
            if (scanTime >= 5f && uType.chainable) {
                Tmp.v1.trns(rotation(), uType.segmentOffset / 2f).add(this);
                Tmp.r1.setCentered(Tmp.v1.x, Tmp.v1.y, hitSize());
                Units.nearby(Tmp.r1, u -> {
                    if (u.team == team && u.type == type && u instanceof Millipedec m && m.head() != this && m.isTail() && m.countForward() + countBackward() < uType.maxSegments && m.waitTime() <= 0f && within(u, uType.segmentOffset) && Utils.angleDist(rotation(), angleTo(u)) < uType.angleLimit) {
                        connect(m);
                    }
                });
                scanTime = 0f;
            }
        }
    }

    @Override
    public void wobble() {}

    @Override
    public void read(Reads read) {
        super.read(read);
        if (read.bool()) {
            MillipedeUnitType uType = (MillipedeUnitType) type;
            saveAdd = true;
            int seg = read.s();
            Millipedec current = this;
            for (int i = 0; i < seg; i++) {
                Unit u = type.constructor.get();
                Millipedec w = (Millipedec) u;
                current.child(u);
                w.parent((Unit) current);
                w.head(this);
                w.layer(layer + uType.segmentLayerOffset * i);
                w.weaponIdx(read.b());
                u.read(read);
                current = w;
            }
        }
    }

    @Override
    public void write(Writes write) {
        super.write(write);
        write.bool(isHead());
        if (isHead()) {
            Millipedec ch = (Millipedec) child;
            int amount = 0;
            while (ch != null) {
                amount++;
                ch = (Millipedec) ch.child();
            }
            write.s(amount);

            ch = (Millipedec) child;
            while (ch != null) {
                write.b(weaponIdx);
                ch.write(write);
                ch = (Millipedec) ch.child();
            }
        }
    }

    @Override
    public void readSync(Reads read) {
        super.readSync(read);
        if (read.bool()) {
            MillipedeUnitType uType = (MillipedeUnitType) type;
            saveAdd = true;
            int seg = read.s();
            Millipedec current = this;
            for (int i = 0; i < seg; i++) {
                Unit u = type.constructor.get();
                Millipedec w = (Millipedec) u;
                current.child(u);
                w.parent((Unit) current);
                w.head(this);
                w.layer(layer + uType.segmentLayerOffset * i);
                w.weaponIdx(read.b());
                u.read(read);
                current = w;
            }
        }
    }

    @Override
    public void writeSync(Writes write) {
        super.writeSync(write);
        write.bool(isHead());
        if (isHead()) {
            Millipedec ch = (Millipedec) child;
            int amount = 0;
            while (ch != null) {
                amount++;
                ch = (Millipedec) ch.child();
            }
            write.s(amount);

            ch = (Millipedec) child;
            while (ch != null) {
                write.b(weaponIdx);
                ch.write(write);
                ch = (Millipedec) ch.child();
            }
        }
    }

    @Override
    public boolean removing() {
        return removing;
    }

    @Override
    public boolean saveAdd() {
        return saveAdd;
    }

    @Override
    public byte weaponIdx() {
        return weaponIdx;
    }

    @Override
    public float layer() {
        return layer;
    }

    @Override
    public float regenTime() {
        return regenTime;
    }

    @Override
    public float scanTime() {
        return scanTime;
    }

    @Override
    public float splitHealthDiv() {
        return splitHealthDiv;
    }

    @Override
    public float waitTime() {
        return waitTime;
    }

    @Override
    public int childId() {
        return childId;
    }

    @Override
    public int headId() {
        return headId;
    }

    @Override
    public Unit child() {
        return child;
    }

    @Override
    public Unit head() {
        return head;
    }

    @Override
    public Unit parent() {
        return parent;
    }

    @Override
    public Unit tail() {
        return tail;
    }

    @Override
    public void child(Unit value) {
        child = value;
    }

    @Override
    public void childId(int value) {
        childId = value;
    }

    @Override
    public void head(Unit value) {
        head = value;
    }

    @Override
    public void headId(int value) {
        headId = value;
    }

    @Override
    public void layer(float value) {
        layer = value;
    }

    @Override
    public void parent(Unit value) {
        parent = value;
    }

    @Override
    public void regenTime(float value) {
        regenTime = value;
    }

    @Override
    public void removing(boolean value) {
        removing = value;
    }

    @Override
    public void saveAdd(boolean value) {
        saveAdd = value;
    }

    @Override
    public void scanTime(float value) {
        scanTime = value;
    }

    @Override
    public void splitHealthDiv(float value) {
        splitHealthDiv = value;
    }

    @Override
    public void tail(Unit value) {
        tail = value;
    }

    @Override
    public void waitTime(float value) {
        waitTime = value;
    }

    @Override
    public void weaponIdx(byte value) {
        weaponIdx = value;
    }
}

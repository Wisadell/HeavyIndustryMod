package heavyindustry.entities.other;

import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.noise.*;
import arc.util.pooling.*;
import arc.util.pooling.Pool.*;
import mindustry.entities.*;
import mindustry.entities.part.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import heavyindustry.entities.other.Components.*;
import heavyindustry.struct.*;

import java.util.*;

public final class Particles {
    /**
     * The entity class of particles defines entity objects that can be drawn and updated.
     * The trajectory of a particle can be changed by setting its speed, position, and deflection method. Typically, this particle has an upper limit on its quantity and should be safe in performance under normal circumstances.
     * Comes with controllable trailing.
     */
    public static class Particle extends Decal implements ExtraVariableComp, Iterable<Cloud> {
        public Map<String, Object> extraVar = new CollectionObjectMap<>();

        private static int counter = 0;
        /** The maximum number of coexisting particles, when the total amount is greater than this number, creating new particles will clear the first generated particle. */
        public static int maxAmount = 1024;

        protected static final ObjectSet<Particle> all = new ObjectSet<>();
        protected static final Seq<Particle> temp = new Seq<>();

        protected Vec2 startPos = new Vec2();
        protected float clipSize;

        Cloud currentCloud, firstCloud;
        int cloudCount;

        public int maxCloudCounts = -1;

        public Particle parent;

        /** Particle velocity, vector. */
        public Vec2 speed = new Vec2();
        /** The current size of the particle. */
        public float size;

        public float defSpeed;
        public float defSize;

        /** The particle model determines the behavior of the particle. */
        public ParticleModel model;
        public float layer;

        public static int count(){
            return all.size;
        }

        public float cloudCount() {
            return cloudCount;
        }

        public static Seq<Particle> get(Boolf<Particle> filter){
            temp.clear();
            for(Particle particle : all){
                if(filter.get(particle)) temp.add(particle);
            }
            return temp;
        }

        @Override
        public void add(){
            index__all = Groups.all.addIndex(this);
            index__draw = Groups.draw.addIndex(this);

            all.add(this);
            counter++;

            currentCloud = Pools.get(Cloud.class, Cloud::new, 65536).obtain();
            currentCloud.x = x;
            currentCloud.y = y;
            currentCloud.size = 0;
            currentCloud.color.set(model.trailColor(this));

            firstCloud = currentCloud;

            added = true;

            model.init(this);

            if(counter >= maxAmount){
                remove();
            }
        }

        @Override
        public void draw(){
            float l = Draw.z();
            Draw.z(layer);

            if (parent != null){
                x += parent.x;
                y += parent.y;
            }

            model.draw(this);

            if(currentCloud != null){
                model.drawTrail(this);
            }

            if (parent != null){
                x -= parent.x;
                y -= parent.y;
            }

            Draw.z(l);
            Draw.reset();
        }

        @Override
        public void update(){
            model.deflect(this);

            x += speed.x*Time.delta;
            y += speed.y*Time.delta;

            size = model.currSize(this);

            model.update(this);

            Cloud c = Pools.get(Cloud.class, Cloud::new, 65536).obtain();
            c.x = parent == null? x: x + parent.x;
            c.y = parent == null? y: y + parent.y;
            c.size = size;
            c.color.set(model.trailColor(this));

            c.perCloud = currentCloud;
            currentCloud.nextCloud = c;

            currentCloud = c;

            cloudCount++;

            for(Cloud cloud: currentCloud){
                model.updateTrail(this, cloud);
            }

            boolean mark = false;
            while(firstCloud.nextCloud != null){
                if(maxCloudCounts > 0 && cloudCount > maxCloudCounts || model.isFaded(this, firstCloud)){
                    mark = !(maxCloudCounts > 0 && cloudCount > maxCloudCounts);
                    popFirst();
                }else break;
            }

            if(!mark && (parent != null && !parent.isAdded() || model.isFinal(this))){
                popFirst();
                if (cloudCount > 4) popFirst();
            }

            if(cloudCount <= 4 && model.isFinal(this)) remove();
        }

        private void popFirst() {
            Cloud n = firstCloud.nextCloud;
            n.perCloud = null;
            Pools.free(firstCloud);
            firstCloud = n;
            cloudCount--;
        }

        @Override
        public void remove() {
            if (added) {
                Groups.all.removeIndex(this, index__all);
                index__all = -1;
                Groups.draw.removeIndex(this, index__draw);
                index__draw = -1;
                Groups.queueFree(this);

                all.remove(this);
                counter--;
                added = false;
            }
        }

        @Override
        public int classId(){
            return 102;
        }

        @Override
        public float clipSize(){
            return clipSize = Math.max(Tmp.v1.set(x, y).sub(startPos).len(), clipSize);
        }

        @Override
        public void reset(){
            added = false;
            parent = null;
            id = EntityGroup.nextId();
            lifetime = 0;
            region = null;
            rotation = 0;
            time = 0;
            x = 0;
            y = 0;

            maxCloudCounts = -1;

            speed.setZero();
            startPos.setZero();

            layer = 0;
            clipSize = 0;

            while(firstCloud.nextCloud != null){
                popFirst();
            }
            Pools.free(firstCloud);

            currentCloud = null;
            firstCloud = null;

            cloudCount = 0;
            size = 0;
            extra().clear();

            model = null;

            color.set(Color.white);
        }

        @Override
        public Iterator<Cloud> iterator() {
            return currentCloud.iterator();
        }

        @Override
        public Map<String, Object> extra() {
            return extraVar;
        }
    }

    public static class Cloud implements Poolable, Iterable<Cloud> {
        public final Color color = new Color();

        public float x, y, size;
        public Cloud perCloud, nextCloud;

        Itr itr = new Itr();

        public void draw(){
            draw(1, 1);
        }

        public void draw(float modulate, float modulateNext){
            Draw.color(color);

            if(perCloud != null && nextCloud != null){
                float angle = Angles.angle(x - perCloud.x, y - perCloud.y);
                float dx1 = Angles.trnsx(angle + 90, size * modulate);
                float dy1 = Angles.trnsy(angle + 90, size * modulate);
                angle = Angles.angle(nextCloud.x - x, nextCloud.y - y);
                float dx2 = Angles.trnsx(angle + 90, nextCloud.size * modulateNext);
                float dy2 = Angles.trnsy(angle + 90, nextCloud.size * modulateNext);

                Fill.quad(x + dx1, y + dy1, x - dx1, y - dy1, nextCloud.x - dx2, nextCloud.y - dy2, nextCloud.x + dx2, nextCloud.y + dy2);
            }else if(perCloud == null && nextCloud != null){
                float angle = Angles.angle(nextCloud.x - x, nextCloud.y - y);
                float dx2 = Angles.trnsx(angle + 90, nextCloud.size * modulate);
                float dy2 = Angles.trnsy(angle + 90, nextCloud.size * modulate);

                Fill.quad(x, y, x, y, nextCloud.x - dx2, nextCloud.y - dy2, nextCloud.x + dx2, nextCloud.y + dy2);
            }
        }

        @Override
        public void reset(){
            x = 0;
            y = 0;
            size = 0;
            color.set(Color.clear);

            perCloud = null;
            nextCloud = null;
        }

        @Override
        public Iterator<Cloud> iterator(){
            itr.reset();
            return itr;
        }

        class Itr implements Iterator<Cloud>{
            Cloud curr = Cloud.this;

            public void reset(){
                curr = Cloud.this;
            }

            @Override
            public boolean hasNext(){
                return curr.perCloud != null;
            }

            @Override
            public Cloud next(){
                return curr = curr.perCloud;
            }
        }
    }

    public static class ParticleModel {
        /**
         * Create an instance of a particle using this model.
         * @param x The x-coordinate at the time of particle creation.
         * @param y The y-coordinate at the time of particle creation.
         * @param color Particle Color.
         * @param sx The x-component of the initial velocity of particle motion.
         * @param sy The y-component of the initial velocity of particle motion.
         * @param size Particle size.
         */
        public Particle create(float x, float y, Color color, float sx, float sy, float size){
            return create(x, y, color, sx, sy, size, Layer.effect);
        }

        /**
         * Create an instance of a particle using this model.
         * @param parent The parent particle to which the particle belongs.
         * @param x The x-coordinate at the time of particle creation.
         * @param y The y-coordinate at the time of particle creation.
         * @param color Particle Color.
         * @param sx The x-component of the initial velocity of particle motion.
         * @param sy The y-component of the initial velocity of particle motion.
         * @param size Particle size.
         */
        public Particle create(Particle parent, float x, float y, Color color, float sx, float sy, float size){
            return create(parent, x, y, color, sx, sy, size, Layer.effect);
        }

        /**
         * Create an instance of a particle using this model.
         * @param x The x-coordinate at the time of particle creation.
         * @param y The y-coordinate at the time of particle creation.
         * @param color Particle Color.
         * @param sx The x-component of the initial velocity of particle motion.
         * @param sy The y-component of the initial velocity of particle motion.
         * @param size Particle size.
         * @param layer The layer where the particles are located is only used in the drawing process.
         */
        public Particle create(float x, float y, Color color, float sx, float sy, float size, float layer){
            return create(null, x, y, color, sx, sy, size, layer);
        }

        /**
         * Create an instance of a particle using this model.
         * @param parent The parent particle to which the particle belongs.
         * @param x The x-coordinate at the time of particle creation
         * @param y The y-coordinate at the time of particle creation.
         * @param color Particle Color.
         * @param sx The x-component of the initial velocity of particle motion.
         * @param sy The y-component of the initial velocity of particle motion.
         * @param size Particle size.
         * @param layer The layer where the particles are located is only used in the drawing process.
         */
        public Particle create(Particle parent, float x, float y, Color color, float sx, float sy, float size, float layer){
            Particle ent = Pools.obtain(Particle.class, Particle::new);
            ent.parent = parent;
            ent.x = x;
            ent.y = y;
            ent.color.set(color);
            ent.layer = layer;
            ent.startPos.set(x, y);
            ent.speed.set(sx, sy);
            ent.defSpeed = ent.speed.len();
            ent.defSize = size;
            ent.size = currSize(ent);

            ent.model = this;
            ent.add();

            return ent;
        }

        public void draw(Particle p){}

        public void updateTrail(Particle p, Cloud c){}

        public void update(Particle p){}

        public void deflect(Particle p){}

        public void drawTrail(Particle c) {}

        public void init(Particle particle) {}

        public boolean isFinal(Particle p){
            return false;
        }

        public Color trailColor(Particle p){
            return null;
        }

        public float currSize(Particle p){
            return p.defSize;
        }

        public boolean isFaded(Particle p, Cloud cloud){
            return false;
        }
    }

    public static class MultiParticleModel extends ParticleModel {
        public ParticleModel[] models;

        public MultiParticleModel(ParticleModel... models){
            this.models = models;
        }

        @Override
        public void draw(Particle p){
            for(ParticleModel model: models){
                model.draw(p);
            }
        }

        @Override
        public void drawTrail(Particle c) {
            for (ParticleModel model: models) {
                model.drawTrail(c);
            }
        }

        @Override
        public void updateTrail(Particle p, Cloud c){
            for(ParticleModel model: models){
                model.updateTrail(p, c);
            }
        }

        @Override
        public void update(Particle p){
            for(ParticleModel model: models){
                if (model == null) break;
                model.update(p);
            }
        }

        @Override
        public void init(Particle p){
            for(ParticleModel model: models){
                model.init(p);
            }
        }

        @Override
        public Color trailColor(Particle p) {
            Tmp.c1.set(p.color);
            for(ParticleModel model: models) {
                Color c = model.trailColor(p);
                if(c == null) continue;
                Tmp.c1.mul(c);
            }
            return Tmp.c1;
        }

        @Override
        public void deflect(Particle p){
            for(ParticleModel model: models){
                model.deflect(p);
            }
        }

        @Override
        public boolean isFinal(Particle p){
            for(ParticleModel model: models){
                if(model.isFinal(p)) return true;
            }
            return false;
        }

        @Override
        public boolean isFaded(Particle p, Cloud cloud){
            for(ParticleModel model: models){
                if(model.isFaded(p, cloud)) return true;
            }
            return false;
        }

        @Override
        public float currSize(Particle p) {
            float res = Float.MAX_VALUE;

            for(ParticleModel model: models){
                res = Math.min(model.currSize(p), res);
            }

            return res;
        }
    }

    public static class DrawDefaultTrailParticle extends ParticleModel {
        @Override
        public void drawTrail(Particle particle) {
            float n = 0;
            for(Cloud c: particle){
                c.draw(1 - n / particle.cloudCount(), 1 - (n + 1) / particle.cloudCount());
                n++;
            }
        }
    }

    public static class DrawPartsParticle extends ParticleModel {
        public float layer = Layer.effect;
        public Seq<DrawPart> parts = new Seq<>();

        DrawPart.PartParams params = new DrawPart.PartParams();

        @Override
        public void draw(Particle p){
            float z = Draw.z();
            Draw.z(layer);

            params.x = p.x;
            params.y = p.y;
            params.warmup = p.size/p.defSize;
            params.life = p.size/p.defSize;
            params.rotation = p.speed.angle();

            for(DrawPart part: parts){
                part.draw(params);
            }

            Draw.z(z);
        }

        public static DrawPartsParticle getSimpleCircle(float size, Color cc){
            return new DrawPartsParticle(){{
                parts.add(new ShapePart(){{
                    progress = PartProgress.warmup;
                    this.color = cc;
                    circle = true;
                    radius = 0;
                    radiusTo = size;
                }});
            }};
        }
    }

    public static class RandDeflectParticle extends ParticleModel {
        public static final String DEFLECT_ANGLE = "deflectAngle";
        public static final String STRENGTH = "strength";

        public float strength = 1;
        public float deflectAngle = 45;

        public void deflect(Particle p) {
            float angle = Tmp.v1.set(p.speed).scl(-1f).angle();
            float scl = Mathf.clamp(p.speed.len() / p.defSpeed*Time.delta * p.getVar(STRENGTH, strength));
            Tmp.v2.set(p.speed).setAngle(angle + Noise.noise(p.x, p.y, 0.01f, 6.7f) * p.getVar(DEFLECT_ANGLE, deflectAngle)).scl(scl);
            p.speed.add(Tmp.v2);
        }
    }

    public static class ShapeParticle extends ParticleModel {
        public boolean circle = true;
        public int polySides = 4;
        public boolean outline;
        public float outlineStoke = 1.6f;
        public float rotSpeed;

        public float layer = Layer.effect;

        @Override
        public void draw(Particle p){
            float l = Draw.z();
            Draw.z(layer);

            Draw.color(p.color);
            if(circle){
                if(outline){
                    Lines.stroke(outlineStoke);
                    Lines.circle(p.x, p.y, p.size);
                }else Fill.circle(p.x, p.y, p.size);
            }else{
                if(outline){
                    Lines.stroke(outlineStoke);
                    Lines.poly(p.x, p.y, polySides, p.size, Time.time * rotSpeed);
                }else Lines.poly(p.x, p.y, polySides, p.size, Time.time * rotSpeed);
            }

            Draw.z(l);
        }
    }

    public static class SizeVelRelatedParticle extends ParticleModel {
        public float finalThreshold = 0.25f;
        public float fadeThreshold = 0.03f;
        public Interp sizeInterp = Interp.linear;

        @Override
        public boolean isFinal(Particle p){
            return p.speed.len() <= finalThreshold;
        }

        @Override
        public Color trailColor(Particle p){
            return null;
        }

        @Override
        public float currSize(Particle p){
            return p.defSize*sizeInterp.apply(Mathf.clamp(p.speed.len()/p.defSpeed));
        }

        @Override
        public boolean isFaded(Particle p, Cloud cloud){
            return cloud.size < fadeThreshold;
        }
    }

    public static class TargetMoveParticle extends ParticleModel {
        public Floatf<Particle> deflection = e -> 0.2f;
        public Func<Particle, Vec2> dest;

        @Override
        public void deflect(Particle e){
            float from = e.speed.angle();
            Vec2 dest = this.dest.get(e);
            float to = Tmp.v1.set(dest.x, dest.y).sub(e.x, e.y).angle();
            float r = to - from;
            r = r > 180? r-360: r < -180? r+360: r;
            e.speed.rotate(r*deflection.get(e)*Time.delta);
        }

        @Override
        public boolean isFinal(Particle e){
            Vec2 dest = this.dest.get(e);
            return Mathf.len(e.x - dest.x, e.y - dest.y) <= 2f;
        }
    }

    public static class TimeParticle extends ParticleModel {
        public static final String BEGIN = "begin";
        public static final String LIFE_TIME = "lifeTime";
        public static final String PROGRESS = "progress";

        public float defLifeMin = 180, defLifeMax = 180;
        public boolean speedRelated;

        @Override
        public void init(Particle particle) {
            particle.setVar(BEGIN, Time.time);
        }

        @Override
        public void update(Particle p) {
            float lifeTime = p.getVar(LIFE_TIME, () -> Mathf.random(defLifeMin, defLifeMax));
            float time = Time.time - p.getVar(BEGIN, 0f);

            float prog = 1 - Mathf.clamp(time / lifeTime);
            p.setVar(PROGRESS, prog);

            if (speedRelated){
                p.speed.setLength(p.defSpeed*prog);
            }
        }

        @Override
        public float currSize(Particle p) {
            return p.defSize*p.getVar(PROGRESS, 0f);
        }

        @Override
        public boolean isFinal(Particle p) {
            return p.getVar(PROGRESS, 0f) <= 0f;
        }
    }

    public static class TrailFadeParticle extends ParticleModel {
        public float trailFade = 0.075f;
        public Color fadeColor;
        public float colorLerpSpeed = 0.03f;
        public boolean linear = false;

        @Override
        public void updateTrail(Particle p, Cloud c){
            c.size = linear? Mathf.approachDelta(c.size, 0, trailFade): Mathf.lerpDelta(c.size, 0, trailFade);
            if(fadeColor != null) c.color.lerp(fadeColor, colorLerpSpeed);
        }
    }
}

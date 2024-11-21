package heavyindustry.entities.other;

import arc.func.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.pooling.*;
import arc.util.pooling.Pool.*;
import mindustry.graphics.*;
import heavyindustry.func.*;

import java.util.*;

public final class Lightnings {
    private static final Vec2 last = new Vec2(), self = new Vec2(), next = new Vec2();

    /**
     * A storage container for a single lightning bolt, which stores the start time and vertex information of the lightning bolt.
     * There are a large number of such instances, which should be reused.
     * @since 1.5
     */
    public static class Lightning implements Poolable {
        public final Seq<LightningVertex> vertices = new Seq<>();
        /** The duration of lightning. */
        public float lifeTime;
        /** The transition time of lightning disappearing. */
        public float fadeTime;
        /** Does lightning disappear from the starting point during the fading process. */
        public boolean backFade = false;
        /** Does the overall width of lightning fade out with the duration of lightning. */
        public boolean fade = true;
        /** The time when lightning was created. */
        public float startTime;
        /** The cutting size of this lightning bolt is used for image cropping during drawing. */
        public float clipSize;
        /** The width of lightning. */
        public float width;

        /** Interpolation function for lightning width. */
        public Interp lerp = Interp.linear;
        /** The trigger for each segment of lightning will be called once each segment of lightning is partially generated, passing in the current vertex and the previous vertex. */
        public Cons2<LightningVertex, LightningVertex> trigger;

        /**
         * If the spread speed of lightning is not set, {@link Lightning#time} will be used to determine the time when lightning completely appears.
         * @deprecated Standardization, this API will no longer be valid.
         */
        @Deprecated
        public float speed;
        /** The time from the generation of lightning to its full manifestation is valid when {@link Lightning#speed} is not set. */
        public float time;
        public float counter, lengthMargin;

        public boolean headClose, endClose;

        protected float totalLength;

        protected int cursor;

        protected boolean enclosed;

        public static Lightning create(LightningGenerator generator, float width, float lifeTime, Interp lerp, float time, Cons2<LightningVertex, LightningVertex> trigger){
            return create(generator, width, lifeTime, lifeTime, lerp, time, true, false, trigger);
        }

        public static Lightning create(LightningGenerator generator, float width, float lifeTime, float fadeTime, Interp lerp, float time, boolean fade, boolean backFade, Cons2<LightningVertex, LightningVertex> trigger){
            Lightning result = Pools.obtain(Lightning.class, Lightning::new);
            result.width = width;
            result.time = time;
            result.startTime = Time.time;
            result.lifeTime = lifeTime;
            result.fadeTime = fadeTime;
            result.lerp = lerp;
            result.fade = fade;
            result.backFade = backFade;
            result.trigger = trigger;

            generator.setCurrentGen(result);

            LightningVertex last = null;
            for(LightningVertex vertex: generator){
                result.vertices.add(vertex);
                if(last != null){
                    result.totalLength += Mathf.len(vertex.x - last.x, vertex.y - last.y);
                }
                last = vertex;
            }
            result.enclosed = generator.isEnclosed();
            result.clipSize = generator.clipSize();

            return result;
        }

        private Lightning(){}

        /** Update the lightning status once. */
        public void update(){
            if(time == 0 && cursor < vertices.size){
                LightningVertex per = null;
                for(LightningVertex vertex: vertices){
                    if(per != null){
                        per.progress = 1;
                        vertex.valid = true;
                        if(trigger != null) trigger.get(per, vertex);
                    }
                    per = vertex;
                }
                cursor = vertices.size;
            }else{
                float increase = vertices.size / time * Time.delta;

                while(increase > 0){
                    if(cursor == 0){
                        cursor++;
                    }

                    if(cursor >= vertices.size) break;

                    LightningVertex per = vertices.get(cursor - 1), curr = vertices.get(cursor);
                    float delta = Math.min(increase, 1 - per.progress);
                    per.progress += delta;
                    increase -= delta;

                    if(per.progress >= 1){
                        curr.valid = true;
                        if(trigger != null) trigger.get(per, curr);
                        cursor++;
                    }
                }
            }

            for(LightningVertex vertex: vertices){
                if(!vertex.isEnd && !vertex.isStart && vertex.valid) vertex.update();
            }
        }

        /**
         * Draw this lightning bolt.
         * @param x Draw the origin x-coordinate of lightning.
         * @param y Draw the origin y-coordinate of lightning.
         */
        public void draw(float x, float y){
            float lerp = Mathf.clamp(this.lerp.apply(Mathf.clamp((lifeTime - (Time.time - startTime)) / fadeTime)));
            float del = backFade? (1 - lerp) * vertices.size: 0;

            if (!fade) lerp = 1;

            for(int i = 2; i <= vertices.size; i++){
                LightningVertex v1 = i - 3 >= 0 ? vertices.get(i - 3) : enclosed ? vertices.get(Mathf.mod(i - 3, vertices.size)) : null,
                        v2 = vertices.get(i - 2),
                        v3 = vertices.get(i - 1),
                        v4 = i < vertices.size? vertices.get(i): enclosed ? vertices.get(Mathf.mod(i, vertices.size)) : null;

                float lastOffX, lastOffY;
                float nextOffX, nextOffY;

                float fade = Math.min(del, 1);
                del -= fade;
                if(!v2.valid) break;

                self.set(v3.x, v3.y).sub(v2.x, v2.y);

                if(v1 != null){
                    last.set(v2.x, v2.y).sub(v1.x, v1.y);

                    float aveAngle = (last.angle() + self.angle())/2;
                    float off = width / 2 * lerp / Mathf.cosDeg(aveAngle - last.angle());

                    lastOffX = Angles.trnsx(aveAngle + 90, off);
                    lastOffY = Angles.trnsy(aveAngle + 90, off);
                }else{
                    Tmp.v1.set(self).rotate90(1).setLength(width / 2 * lerp);
                    lastOffX = Tmp.v1.x;
                    lastOffY = Tmp.v1.y;
                }

                if(v4 != null){
                    next.set(v4.x, v4.y).sub(v3.x, v3.y);
                    float aveAngle = (self.angle() + next.angle()) / 2;
                    float off = width / 2 * lerp / Mathf.cosDeg(aveAngle - self.angle());

                    nextOffX = Angles.trnsx(aveAngle + 90, off);
                    nextOffY = Angles.trnsy(aveAngle + 90, off);
                }else{
                    Tmp.v1.set(self).rotate90(1).setLength(width / 2 * lerp);
                    nextOffX = Tmp.v1.x;
                    nextOffY = Tmp.v1.y;
                }

                lastOffX *= lerp;
                lastOffY *= lerp;
                nextOffX *= lerp;
                nextOffY *= lerp;

                float orgX = x + v2.x, orgY = y + v2.y;
                float fadX = Tmp.v1.x*fade, fadY = Tmp.v1.y*fade;

                Tmp.v1.set(self).scl(v2.progress);
                if((v2.isStart && !headClose) || (v3.isEnd && !endClose)){
                    float l = v2.isStart ? v2.progress: 1 - v2.progress;
                    float f = v2.isStart ? fade : 1 - fade;
                    Fill.quad(orgX + fadX + lastOffX*f, orgY + fadY + lastOffY*f, orgX + fadX - lastOffX*f, orgY + fadY - lastOffY*f, orgX + Tmp.v1.x - nextOffX*l, orgY + Tmp.v1.y - nextOffY*l, orgX + Tmp.v1.x + nextOffX*l, orgY + Tmp.v1.y + nextOffY*l);
                }else{
                    Fill.quad(orgX + fadX + lastOffX, orgY + fadY + lastOffY, orgX + fadX - lastOffX, orgY + fadY - lastOffY, orgX + Tmp.v1.x - nextOffX, orgY + Tmp.v1.y - nextOffY, orgX + Tmp.v1.x + nextOffX, orgY + Tmp.v1.y + nextOffY);
                }

                Drawf.light(orgX, orgY, orgX + Tmp.v1.x, orgY + Tmp.v1.y, width * 32, Draw.getColor(), Draw.getColor().a);

                v2.draw(x, y);
            }
        }

        @Override
        public void reset(){
            for(LightningVertex vertex: vertices){
                Pools.free(vertex);
            }
            vertices.clear();
            counter = 0;
            width = 0;
            time = 0;
            cursor = 0;
            lifeTime = 0;
            enclosed = false;
            lerp = null;
            lengthMargin = 0;
            startTime = 0;
            clipSize = 0;
            trigger = null;
        }
    }

    /**
     * Lightning container, using a lightning generator to generate lightning, processed and drawn by the container, usually used for storing a type of lightning in the same container.
     * @since 1.5
     */
    public static class LightningContainer implements Iterable<Lightning> {
        /**
         * The time required for lightning to occur from generation to complete appearance will be evenly distributed among each lightning segment, with fps being the current frame rate.
         * But if this value is 0, lightning will appear immediately.
         */
        public float time = 0;
        /**
         * When the diffusion speed of lightning is less than or equal to 0, the path diffusion calculation method provided by time is used by default. Otherwise, the given speed is used to process the diffusion of lightning (unit:/tick)
         * @deprecated Standardization, this API is no longer available.
         */
        @Deprecated
        public float speed = 0;
        /** The existence time of lightning. */
        public float lifeTime = 30;
        /** The transition time of lightning disappearance, if not set, the disappearance transition time is equal to the existence time of lightning. */
        public float fadeTime = -1;
        /** Does the overall width of lightning fade out with the duration of lightning. */
        public boolean fade = true;
        /** Does lightning disappear from the starting point during the fading process. */
        public boolean backFade = false;
        /** Random intervals of lightning width for each segment. */
        public float minWidth = 2.5f, maxWidth = 4.5f;
        /** The attenuation transformer of lightning receives the value of the duration of lightning existence. */
        public Interp lerp = Interp.pow2Out;

        /** The callback function called when creating a lightning branch is generally used to define the sub container properties of the lightning branch. */
        public Cons<Lightning> branchCreated;

        /** Lightning vertex trigger, triggered when a lightning node has arrived, passes in the previous vertex and this vertex. */
        public Cons2<LightningVertex, LightningVertex> trigger;
        public boolean headClose, endClose;

        protected float clipSize;

        protected final Seq<Lightning> lightnings = new Seq<>();

        /** Create a new lightning bolt in the container using the provided lightning generator. */
        public void create(LightningGenerator generator){
            generator.branched(branchCreated);
            Lightning lightning = Lightning.create(generator, Mathf.random(minWidth, maxWidth), lifeTime, fadeTime > 0 ? fadeTime : lifeTime, lerp, time, fade, backFade, trigger);
            lightning.headClose = headClose;
            lightning.endClose = endClose;
            lightnings.add(lightning);
        }

        @Override
        public Iterator<Lightning> iterator(){
            return lightnings.iterator();
        }

        /** Update the status of all sub lightning in the current container once. */
        public void update(){
            Iterator<Lightning> itr = lightnings.iterator();
            while(itr.hasNext()){
                Lightning lightning = itr.next();
                clipSize = Math.max(clipSize, lightning.clipSize);

                float progress = (Time.time - lightning.startTime)/lifeTime;
                if(progress > 1){
                    itr.remove();
                    Pools.free(lightning);
                    clipSize = 0;
                    continue;
                }

                lightning.update();
            }
        }

        /**
         * Draw the container, which will draw all the lightning saved in the container.
         * @param x Draw the origin x-coordinate of lightning.
         * @param y Draw the origin y-coordinate of lightning.
         */
        public void draw(float x, float y){
            for(Lightning lightning: lightnings){
                lightning.draw(x, y);
            }
        }

        public float clipSize(){
            return clipSize;
        }
    }

    /** Lightning branch container, used to draw branch lightning, recursively draws all sub-branches. */
    public static class PoolLightningContainer extends LightningContainer implements Poolable {
        public static PoolLightningContainer create(float lifeTime, float minWidth, float maxWidth){
            PoolLightningContainer result = Pools.obtain(PoolLightningContainer.class, PoolLightningContainer::new);
            result.lifeTime = lifeTime;
            result.minWidth = minWidth;
            result.maxWidth = maxWidth;

            return result;
        }

        @Override
        public void reset(){
            time = 0;
            lifeTime = 0;
            clipSize = 0;
            maxWidth = 0;
            minWidth = 0;
            lerp = f -> 1 - f;
            branchCreated = null;
            trigger = null;

            for(Lightning lightning: lightnings){
                Pools.free(lightning);
            }
            lightnings.clear();
        }
    }

    /**
     * The lightning vertex container stores necessary information for a vertex and a drawing progress timer.
     * There are a large number of such instances, which should be reused.
     * @since 1.5
     */
    public static class LightningVertex implements Pool.Poolable {
        public float x, y;
        public float angle;

        public boolean isStart;
        public boolean isEnd;

        public boolean valid;
        public float progress;

        public Lightning branchOther;

        protected void draw(float x, float y){
            if(branchOther != null) branchOther.draw(x, y);
        }

        public void update(){
            if(branchOther != null) branchOther.update();
        }

        @Override
        public void reset(){
            if(branchOther != null) Pools.free(branchOther);

            valid = false;
            progress = 0;
            x = y = 0;
            angle = 0;
            branchOther = null;
            isStart = false;
            isEnd = false;
        }
    }

    /**
     * A generator for circular lightning that generates lightning vertices using a specified center and radius.
     * @since 1.5
     */
    public static class CircleGenerator extends LightningGenerator {
        /** The radius of the circle on which lightning is based. */
        public float radius = 16;
        /** The original starting angle of the circle will affect the lightning propagation starting point of the circle generated when time is not zero. */
        public float originAngle;
        /** The rotation direction of a circle is counterclockwise if it is greater than 0, otherwise it is clockwise. */
        public int directory = 1;
        /** Whether this circle is closed or not will determine whether the head and tail of the lightning are connected or disconnected. */
        public boolean enclosed = true;

        protected Vec2 rad = new Vec2();
        protected float currentRotated;

        protected boolean first;
        protected LightningVertex firstOne;

        @Override
        public void reset(){
            super.reset();
            rad.set(1, 0).setLength(radius).setAngle(originAngle);
            currentRotated = 0;
            first = true;
            firstOne = null;
        }

        @Override
        public boolean hasNext(){
            return super.hasNext() && currentRotated < 360;
        }

        @Override
        protected void handleVertex(LightningVertex vertex){
            float step = seed.random(minInterval, maxInterval);
            float rotated = step / (Mathf.pi * radius/180) * (directory >= 0 ? 1 : -1);

            if(rotated + currentRotated >= 360){
                if (firstOne == null){
                    currentRotated = 361;
                    return;
                }

                vertex.isEnd = !enclosed;
                if(enclosed){
                    vertex.x = firstOne.x;
                    vertex.y = firstOne.y;
                }
                currentRotated = 360;
            }else{
                currentRotated += rotated;

                float offset = seed.random(-maxSpread, maxSpread);
                Tmp.v2.set(Tmp.v1.set(rad.rotate(rotated))).setLength(offset).scl(offset < 0 ? -1 : 1);
                Tmp.v1.add(Tmp.v2);

                vertex.x = Tmp.v1.x;
                vertex.y = Tmp.v1.y;
            }

            if(first){
                vertex.valid = true;
                vertex.isStart = !enclosed;
                if(enclosed){
                    firstOne = vertex;
                }
                first = false;
            }
        }

        @Override
        public boolean isEnclosed(){
            return enclosed;
        }

        @Override
        public float clipSize(){
            return radius + maxSpread;
        }
    }

    /**
     * Random path lightning generator, providing the total length of the starting path to generate a random lightning path.
     * @since 1.5
     */
    public static class RandomGenerator extends LightningGenerator {
        public float maxLength = 80;
        public float maxDeflect = 70;
        public float originAngle = Float.MIN_VALUE;

        protected float currLength;
        protected Vec2 curr = new Vec2();

        protected boolean first;
        protected float maxDistance;

        @Override
        public void reset(){
            super.reset();
            currLength = 0;
            maxDistance = 0;
            first = true;
            if(originAngle == Float.MIN_VALUE){
                curr.rnd(0.001f);
            }else{
                curr.set(0.001f, 0).setAngle(originAngle);
            }
        }

        @Override
        protected void handleVertex(LightningVertex vertex){
            if(first){
                vertex.isStart = true;
                vertex.valid = true;
                first = false;
            }else{
                float distance = seed.random(minInterval, maxInterval);
                if(currLength + distance > maxLength){
                    vertex.isEnd = true;
                }

                currLength += distance;
                Tmp.v1.setLength(distance).setAngle(curr.angle() + seed.random(-maxDeflect, maxDeflect));
                curr.add(Tmp.v1);
                maxDistance = Math.max(maxDistance, curr.len());
            }

            vertex.x = curr.x;
            vertex.y = curr.y;
        }

        @Override
        public float clipSize(){
            return maxDistance;
        }

        @Override
        public boolean hasNext(){
            return super.hasNext() && currLength < maxLength;
        }
    }

    /**
     * Shrink the generator of lightning, which will generate lightning that spreads inward to the center within a certain range.
     * @since 1.5
     */
    public static class ShrinkGenerator extends LightningGenerator {
        public float minRange, maxRange;

        protected Vec2 vec = new Vec2();
        protected float distance;
        protected float currentDistance;
        protected boolean first;

        @Override
        public void reset(){
            super.reset();
            vec.rnd(distance = seed.random(minRange, maxRange));
            currentDistance = distance;
            first = true;
        }

        @Override
        public boolean hasNext(){
            return super.hasNext() && currentDistance > 0;
        }

        @Override
        protected void handleVertex(LightningVertex vertex){
            currentDistance -= seed.random(minInterval, maxInterval);

            if(currentDistance > minInterval){
                if(first){
                    Tmp.v2.set(vec);
                }else{
                    float offset = seed.random(-maxSpread, maxSpread);
                    Tmp.v2.set(vec).setLength(currentDistance).add(Tmp.v1.set(vec).rotate90(1).setLength(offset).scl(offset < 0 ? -1 : 1));
                }
            }else{
                currentDistance = 0;
                Tmp.v2.setZero();
                vertex.isEnd = true;
            }

            vertex.x = Tmp.v2.x;
            vertex.y = Tmp.v2.y;

            if(first){
                vertex.isStart = true;
                vertex.valid = true;
                first = false;
            }
        }

        @Override
        public float clipSize(){
            return 0;
        }
    }

    /**
     * Vector lightning generator, generating lightning that spreads in a straight line along a specified vector.
     * @since 1.5
     */
    public static class VectorLightningGenerator extends LightningGenerator {
        public Vec2 vector = new Vec2();

        float distance;
        float currentDistance;
        protected boolean first;

        @Override
        public void reset(){
            super.reset();
            currentDistance = 0;
            first = true;
            distance = vector.len();
        }

        @Override
        public boolean hasNext(){
            return super.hasNext() && currentDistance < distance;
        }

        @Override
        protected void handleVertex(LightningVertex vertex){
            currentDistance += seed.random(minInterval, maxInterval);

            if(currentDistance < distance - minInterval){
                if(first){
                    Tmp.v2.setZero();
                }else{
                    float offset = seed.random(-maxSpread, maxSpread);
                    Tmp.v2.set(vector).setLength(currentDistance).add(Tmp.v1.set(vector).rotate90(1).setLength(offset).scl(offset < 0 ? -1 : 1));
                }
            }else{
                currentDistance = distance;
                Tmp.v2.set(vector);
                vertex.isEnd = true;
            }

            vertex.x = Tmp.v2.x;
            vertex.y = Tmp.v2.y;

            if(first){
                vertex.isStart = true;
                vertex.valid = true;
                first = false;
            }
        }

        @Override
        public float clipSize(){
            return distance;
        }
    }

    /**
     * The lightning generator base class implements both the {@link Iterator} and {@link Iterable} interfaces, which can use a for-each loop to generate vertices one by one. Each time the iterator is obtained, the generator itself is returned and the iteration state is reset.
     * Note that any change in the generator properties outside the iterator operation will directly affect the distribution of vertices generated by the iteration, and the generator is reusable, producing an unrelated set of vertices with each iteration.
     * <p>Warning: This method is not thread safe, and it is important to avoid iterating over this object simultaneously at all times.
     *
     * @since 1.5
     */
    public static abstract class LightningGenerator implements Iterable<LightningVertex>, Iterator<LightningVertex> {
        public Rand seed = new Rand();

        /** Minimum distance between vertex benchmarks. */
        public float minInterval = 6;
        /** Maximum value of vertex reference position. */
        public float maxInterval = 18;

        /** The higher the dispersion degree of lightning vertices, the farther the vertex offset will be. */
        public float maxSpread = 12.25f;

        /** Probability of branching (per vertex) */
        public float branchChance;
        /** Minimum branch strength. */
        public float minBranchStrength = 0.3f;
        /** Maximum branch strength. */
        public float maxBranchStrength = 0.8f;
        /** Branch creator, passing in the vertex where the branch is located and the strength of the branch, needs to return a lightning generator. Note that any generator object can be passed in, please do not create a new generator. */
        public Func2<LightningVertex, Float, LightningGenerator> branchMaker;

        public Cons<Lightning> branchCreated;
        public Floatp2<LightningVertex, LightningVertex> blockNow;

        protected Lightning curr;

        protected LightningVertex last;
        protected boolean isEnding;

        private float offsetX, offsetY;

        public static final Pool<LightningVertex> vertexPool;

        static {
            Pools.set(LightningVertex.class, vertexPool = new Pool<>(8192, 65536){
                @Override
                protected LightningVertex newObject(){
                    return new LightningVertex();
                }
            });
        }

        public void setCurrentGen(Lightning curr){
            this.curr = curr;
        }

        public void branched(Cons<Lightning> branchCreated){
            this.branchCreated = branchCreated;
        }

        /** Create a branch lightning on vertices using the current branch generator. */
        public void createBranch(LightningVertex vertex){
            float strength = Mathf.clamp(Mathf.random(minBranchStrength, maxBranchStrength));
            LightningGenerator gen = branchMaker.get(vertex, strength);
            gen.setOffset(vertex.x, vertex.y);
            Floatp2<LightningVertex, LightningVertex> old = gen.blockNow;
            gen.blockNow = (l, v) -> old != null ? old.get(l, v) : blockNow != null ? blockNow.get(l, v) : -1;
            vertex.branchOther = Lightning.create(gen, curr.width*strength, curr.lifeTime, curr.fadeTime, curr.lerp, curr.time, curr.fade, curr.backFade, curr.trigger);
            gen.blockNow = old;
            gen.resetOffset();

            vertex.branchOther.vertices.first().isStart = false;

            if(branchCreated != null) branchCreated.get(vertex.branchOther);
        }

        /** This class implements both iterable and iterator interfaces, allowing for a for each loop to generate vertices one by one. This method is not thread safe. */
        @Override
        public synchronized Iterator<LightningVertex> iterator(){
            reset();
            return this;
        }

        public void reset(){
            last = null;
            isEnding = false;
        }

        /** Iterator obtains the next vertex through this method. */
        @Override
        public LightningVertex next(){
            LightningVertex vertex = Pools.obtain(LightningVertex.class, null);
            handleVertex(vertex);
            offsetVertex(vertex);
            afterHandle(vertex);

            float blockLen;
            if(blockNow != null && last != null && (blockLen = blockNow.get(last, vertex)) > 0){
                isEnding = true;
                vertex.isEnd = true;
                float angle = Mathf.angle(vertex.x - last.x, vertex.y - last.y);
                vertex.x = last.x + Angles.trnsx(angle, blockLen);
                vertex.y = last.y + Angles.trnsy(angle, blockLen);
                offsetVertex(vertex);
                afterHandle(vertex);
                return vertex;
            }

            if(!vertex.isStart && !vertex.isEnd && branchChance > 0 && Mathf.chance(branchChance)){
                createBranch(vertex);
            }
            last = vertex;
            return vertex;
        }

        @Override
        public boolean hasNext(){
            return !isEnding;
        }

        /** Called after vertex processing. */
        public void afterHandle(LightningVertex vertex){
            if(last == null) return;
            vertex.angle = Mathf.angle(vertex.x - last.x, vertex.y - last.y);
        }

        public void offsetVertex(LightningVertex vertex){
            vertex.x += offsetX;
            vertex.y += offsetY;
        }

        public void setOffset(float dx, float dy){
            offsetX = dx;
            offsetY = dy;
        }

        public void resetOffset(){
            offsetY = offsetX = 0;
        }

        public boolean isEnclosed(){
            return false;
        }

        /** Vertex processing, which assigns attributes such as coordinates to vertices. */
        protected abstract void handleVertex(LightningVertex vertex);

        /** Return the crop size of the current lightning, which should be able to fully draw the lightning. */
        public abstract float clipSize();
    }
}

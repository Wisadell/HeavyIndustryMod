package heavyindustry.world.lightning.generator;

import arc.func.*;
import arc.math.*;
import arc.util.pooling.*;
import heavyindustry.func.*;
import heavyindustry.world.lightning.*;

import java.util.*;

/**
 * The lightning generator base class implements both the Iterator and Iteratable interfaces, which can use a for each loop to generate vertices one by one.
 * Each time the iterator is obtained, the generator itself is returned and the iteration state is reset.
 * <p>Note that any change in the generator properties outside the iterator operation will directly affect the distribution of vertices generated by the iteration,
 * and the generator is reusable, producing an unrelated set of vertices with each iteration.
 * <p>Warning: This method is not thread safe, and it is important to avoid iterating over this object simultaneously at all times.
 */
public abstract class LightningGenerator implements Iterable<LightningVertex>, Iterator<LightningVertex> {
    public static final Pool<LightningVertex> vertexPool;

    static {
        Pools.set(LightningVertex.class, vertexPool = new Pool<>(8192, 65536) {
            @Override
            protected LightningVertex newObject() {
                return new LightningVertex();
            }
        });
    }

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
    /**
     * Branch creator, passing in the vertex where the branch is located and the strength of the branch, needs to return a lightning generator.
     * Note that any generator object can be passed in, please do not create a new generator.
     */
    public Func2<LightningVertex, Float, LightningGenerator> branchMaker;
    public Cons<Lightning> branchCreated;
    public Floatp2<LightningVertex, LightningVertex> blockNow;

    protected Lightning curr;
    protected LightningVertex last;
    protected boolean isEnding;

    private float offsetX, offsetY;

    public void setCurrentGen(Lightning curr) {
        this.curr = curr;
    }

    public void branched(Cons<Lightning> branchCreated) {
        this.branchCreated = branchCreated;
    }

    /**
     * Create a branch lightning on vertices using the current branch generator.
     */
    public void createBranch(LightningVertex vertex) {
        float strength = Mathf.clamp(Mathf.random(minBranchStrength, maxBranchStrength));
        LightningGenerator gen = branchMaker.get(vertex, strength);
        gen.setOffset(vertex.x, vertex.y);
        Floatp2<LightningVertex, LightningVertex> old = gen.blockNow;
        gen.blockNow = (l, v) -> old != null ? old.get(l, v) : blockNow != null ? blockNow.get(l, v) : -1;
        vertex.branchOther = Lightning.create(gen, curr.width * strength, curr.lifeTime, curr.fadeTime, curr.lerp, curr.time, curr.fade, curr.backFade, curr.trigger);
        gen.blockNow = old;
        gen.resetOffset();

        vertex.branchOther.vertices.first().isStart = false;

        if (branchCreated != null) branchCreated.get(vertex.branchOther);
    }

    /**
     * This class implements both iterable and iterator interfaces, allowing for a for each loop to generate vertices one by one. This method is not thread safe.
     */
    @Override
    public synchronized Iterator<LightningVertex> iterator() {
        reset();
        return this;
    }

    public void reset() {
        last = null;
        isEnding = false;
    }

    /**
     * Iterator obtains the next vertex through this method.
     */
    @Override
    public LightningVertex next() {
        LightningVertex vertex = Pools.obtain(LightningVertex.class, null);
        handleVertex(vertex);
        offsetVertex(vertex);
        afterHandle(vertex);

        float blockLen;
        if (blockNow != null && last != null && (blockLen = blockNow.get(last, vertex)) > 0) {
            isEnding = true;
            vertex.isEnd = true;
            float angle = Mathf.angle(vertex.x - last.x, vertex.y - last.y);
            vertex.x = last.x + Angles.trnsx(angle, blockLen);
            vertex.y = last.y + Angles.trnsy(angle, blockLen);
            offsetVertex(vertex);
            afterHandle(vertex);
            return vertex;
        }

        if (!vertex.isStart && !vertex.isEnd && branchChance > 0 && Mathf.chance(branchChance)) {
            createBranch(vertex);
        }
        last = vertex;
        return vertex;
    }

    @Override
    public boolean hasNext() {
        return !isEnding;
    }

    /**
     * Called after vertex processing.
     */
    public void afterHandle(LightningVertex vertex) {
        if (last == null) return;
        vertex.angle = Mathf.angle(vertex.x - last.x, vertex.y - last.y);
    }

    public void offsetVertex(LightningVertex vertex) {
        vertex.x += offsetX;
        vertex.y += offsetY;
    }

    public void setOffset(float dx, float dy) {
        offsetX = dx;
        offsetY = dy;
    }

    public void resetOffset() {
        offsetY = offsetX = 0;
    }

    public boolean isEnclosed() {
        return false;
    }

    /**
     * Vertex processing, which assigns attributes such as coordinates to vertices.
     */
    protected abstract void handleVertex(LightningVertex vertex);

    /**
     * Return the crop size of the current lightning, which should be able to fully draw the lightning.
     */
    public abstract float clipSize();
}

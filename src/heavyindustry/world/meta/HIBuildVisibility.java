package heavyindustry.world.meta;

import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class HIBuildVisibility {
    public static BuildVisibility
            campaignOrSandboxOnly  = new BuildVisibility(() -> state == null || state.isCampaign() || state.rules.infiniteResources);
}

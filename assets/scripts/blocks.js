const lib = require('lib');
//storage
const coreBeStationed = extend(CoreBlock, "core-be-stationed", {
	canBreak(tile) {
		return Vars.state.teams.cores(tile.team()).size > 1;
	},
	canReplace(other) {
		return other.alwaysReplace;
	},
	canPlaceOn(tile, team, rotation) {
		return Vars.state.teams.cores(team).size < 6;
	}
});
exports.coreBeStationed = coreBeStationed;
Object.assign(coreBeStationed, {
    health: 300,
    size: 2,
    itemCapacity: 1000,
    unitCapModifier: 4,
	buildVisibility: BuildVisibility.shown,
	category: Category.effect,
	requirements: ItemStack.with(
		Items.copper, 500,
		Items.lead, 400,
		Items.silicon, 150
	)
});
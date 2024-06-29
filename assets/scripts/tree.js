const lib = require('lib');

const { coreBeStationed } = require('blocks');

lib.addToResearch(coreBeStationed, {
    parent: 'core-shard',
    requirements: ItemStack.with(
        Items.copper, 5000,
        Items.lead, 4000,
        Items.silicon, 1000
    )
});
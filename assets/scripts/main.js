Events.on(EventType.ClientLoadEvent, () => {
    if(Vars.mods.getMod("flameout") != null)Vars.mods.removeMod(Vars.mods.getMod("flameout"));
});
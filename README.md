# A server-side fabric mod that reconfigures bat spawns
This mod prevents new spawns instead of killing spawns that go beyond the custom limits.

Available on: 
- Modrinth: https://modrinth.com/mod/bats-reconfig
- Curseforge: https://www.curseforge.com/minecraft/mc-mods/bats-reconfig

_If installed on a server, individual players do not need to install it as well for this mod to work._

### Features
- Limit bat spawns up to a certain number.
- Limit bat spawns up to a certain max y level.
- Use command: /bats to check how many bats are around you in a non-immediate despawnable area (128x128x128). Accessible to everyone.
- If you have access to server logs, you can check that the mod outputs the position for each detected bat.

### How to use
1. Drop this mod into your minecraft mods folder.
2. After starting up the game once, locate the batsreconfig-common.cfg in your minecraft config folder.
3. In there, you are able to change each feature value. Default values are already set.
4. Restart the game to see new changes.

### Dependencies

These need to be installed into your mods folder for the mod to work.
- Fabric API: https://modrinth.com/mod/fabric-api
- Config API: https://modrinth.com/mod/config-api


<h1 align="center">BlendMC</h1>

<h4 align="center">BlendMC: A Cinematic Camera plugin for Minecraft Spigot that uses Blender motion paths for cutscenes.</h4>

<p align="center">
  <a href="https://twitter.com/BattleDashBR"><img src="https://img.shields.io/badge/Twitter-@BattleDashBR-1da1f2.svg?logo=twitter"></a>
  <a href="https://discord.gg/arkh">
      <img src="https://img.shields.io/discord/590348063122653203.svg?label=Discord&logo=discord&color=778cd4">
  </a>
  
</p>

------

<p align="center">
  <a href="#examples">Examples</a> •
  <a href="#usage">Usage</a> •
  <a href="#api">API</a>
</p>

## Examples

Click for full video:
[![example](https://i.battleda.sh/1df746663a9497ac8c8234161ea907b0.png)](https://i.battleda.sh/b75e6bd0a0ecab074296fb6479dcfb3f.mp4)

## Usage

To use BlendMC, you'll need [Blender 2.8](https://www.blender.org/download/) or newer installed on your computer. Once it's done, you can download the latest release of the BlendMC Exporter Addon [here](https://i.battleda.sh/stuff/mcblend.py/).

1. Install [Blender 2.8](https://www.blender.org/download/)
2. Install the BlendMC Exporter Addon [here](https://i.battleda.sh/stuff/mcblend.py/)
3. Select your camera, go to File > Export > BlendMC Camera Exporter, and save as a `.blendmc` file. Put this in your BlendMC plugin data folder.

## API

Using the API is very simple.

To create a cutscene:

```java
Player player = null; // However you get a player.
BlendCameraAnimation anim = BlendCameraAnimation.parse(
        new File(BlendMC.getInstance().getDataFolder().getAbsolutePath() + "/example.blendmc")); // The fully qualified path to your blendmc file.
// Create a cutscene with the camera start location being the player's current position.
BlendCutscene cutscene = new BlendCutscene(anim, player.getLocation());
```

To play that cutscene:

```java
cutscene.play(player, () -> {
    // this optional callback is ran when the cutscene ends
});
```

If the cutscene is in the wrong direction, you can easily rotate it like this:

```java
// Rotate the entire cutscene 90° right clockwise.
cutscene.rotateAllFrames((float) Math.toRadians(90));
```

# Closing

Pull Requests and issues/feature requests are welcome!

**Note:** You are responsible for things like setting players to spectator mode. BlendMC does not do this automatically.
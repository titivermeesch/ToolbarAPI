# ToolbarAPI

[![](https://jitpack.io/v/titivermeesch/ToolbarAPI.svg)](https://jitpack.io/#titivermeesch/ToolbarAPI)

**THIS IS NOT A SPIGOT PLUGIN, ONLY INSTALL IT IF YOU ARE A DEVELOPER OR YOU USE A PLUGIN THAT DEPEND ON THIS**

ToolbarAPI is an API created to help developers set up toolbar menus without having to create complex inventory
listeners.

ToolbarAPI can register multiple toolbars at the same time, and players can have different toolbars open at the same
time

## Installation

### Gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.titivermeesch:ToolbarAPI:1.0.0'
}
```

### Maven

```xml

<project>
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    <dependency>
        <groupId>com.github.titivermeesch</groupId>
        <artifactId>ToolbarAPI</artifactId>
        <version>1.0.0</version>
    </dependency>
</project>
```

## Usage

```java
class Main {
    public void onEnable() {
        // Create the toolbar manager. This will also register a listener to handle the item clicks
        ToolbarManager toolbarManager = new ToolbarManager(plugin);

        // Create a toolbar and register it
        Toolbar toolbar = new Toolbar("my_toolbar");
        ItemStack compass = new ItemStack(Material.COMPASS);
        ClickableItem clickableCompass = new ClickableItem(compass, e -> e.getPlayer().sendMessage("You clicked the compass!"));
        toolbar.addItem(0, clickableCompass);

        // Register the freshly created toolbar
        toolbarManager.addToolbar(toolbar);
    }
}

// This example shows a toolbar activated in the join event, but it could be triggered by anything
class JoinListeners implements Listeners {
    private final ToolbarManager toolbarManager = Main.getInstance().getToolbarManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        toolbarManager.setToolbar(e.getPlayer(), "my_toolbar");
    }
}
```

## Extra information

Here are a few points that may be useful if you try to debug something or if you want to start contributing

- The plugin registers a listener when you initiate your `ToolbarManager`. This listener will take care of triggering
  the correct execution

## Contributing

Feel free to create a ticket about a feature you would like to see, or create a PR to implement something new.
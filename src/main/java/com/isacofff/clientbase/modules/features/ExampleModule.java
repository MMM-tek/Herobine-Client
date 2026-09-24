package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;

public class ExampleModule extends Module {

    public Setting.NumberSetting test = new Setting.NumberSetting("Test", 100,1,100,1);
    public Setting.ModeSetting test2 = new Setting.ModeSetting("Test2", "1","1", "2", "3");

    public ExampleModule() {
        super("Example", Category.Client);

        this.description = "Feel free to remove this as it is only to show how to make modules.";

        settings.add(test);
        settings.add(test2);
        //You must add them like so for them to appear in the ClickGui
    }

    @Override
    public void onEnable() {
        super.onEnable();
        //When enabled
    }

    @Override
    public void onDisable() {
        super.onDisable();
        //When disabled
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        //Basically forever when enabled
    }

}

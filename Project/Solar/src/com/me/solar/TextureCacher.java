package com.me.solar;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class TextureCacher
{
    public HashMap<String, NinePatch> NinePatchMap = new HashMap<String, NinePatch>();

    public static Texture Load(String Path)
    {
        return new Texture(Gdx.files.internal(Path));
    }

    public void LoadTextures()
    {
//        NinePatch n = new NinePatch(TextureCacher.Load("data/button_regular.9.png"));
//        NinePatchMap.put("button_regular", n);
//        
//        n = new NinePatch(TextureCacher.Load("data/button_selected.9.png"));
//        NinePatchMap.put("button_selected", n);
//        
//        n = new NinePatch(TextureCacher.Load("data/button_active.9.png"));
//        NinePatchMap.put("button_active", n);
    }
}

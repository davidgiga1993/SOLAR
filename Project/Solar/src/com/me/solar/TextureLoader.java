package com.me.solar;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class TextureLoader
{
    public HashMap<String,TextureRegion> TextureMap = new HashMap<String, TextureRegion>();
    
    public static Texture Load(String Path)
    {
        return new Texture(Gdx.files.internal(Path));
    }
    
    public void LoadTextures()
    {        
        //NinePatch n = new NinePatch(TextureLoader.Load("assets/button_regular.png"));        
    }
}

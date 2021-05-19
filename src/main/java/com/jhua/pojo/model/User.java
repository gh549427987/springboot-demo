package com.jhua.pojo.model;

import com.sun.prism.shader.Solid_TextureRGB_AlphaTest_Loader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String username;
    private String password;
    private String perms;

}

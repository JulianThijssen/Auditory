package com.auditory.components;

import com.auditory.Component;

public class Camera extends Component {
	public static final float   DEFAULT_FOV = 90;
	public static final float   DEFAULT_ASPECTRATIO = 1;
	public static final float   DEFAULT_ZNEAR = 0.3f;
	public static final float   DEFAULT_ZFAR = 100f;
	public static final boolean DEFAULT_PERSPECTIVE = true;
	
	public float   fieldOfView = DEFAULT_FOV;
	public float   aspectRatio = DEFAULT_ASPECTRATIO;
	public float   zNear       = DEFAULT_ZNEAR;
	public float   zFar        = DEFAULT_ZFAR;
	public boolean perspective = DEFAULT_PERSPECTIVE;
}

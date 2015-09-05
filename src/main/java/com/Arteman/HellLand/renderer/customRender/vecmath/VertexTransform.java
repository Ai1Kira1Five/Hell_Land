package com.Arteman.HellLand.renderer.customRender.vecmath;

public interface VertexTransform {

  void apply(Vertex vertex);

  void apply(Vector3d vec);

  void applyToNormal(Vector3f vec);

}

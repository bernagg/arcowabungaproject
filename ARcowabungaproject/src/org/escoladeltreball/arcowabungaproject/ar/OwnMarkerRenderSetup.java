package org.escoladeltreball.arcowabungaproject.ar;

//import com.badlogic.gdx.graphics.g3d.loaders.ogre.mesh.Mesh;

import geo.GeoObj;
//import gl.Color;
import gl.CustomGLSurfaceView;
import gl.GL1Renderer;
import gl.GLCamera;
import gl.GLFactory;
//import gl.animations.AnimationFaceToCamera;
import gl.scenegraph.MeshComponent;
//import gl.scenegraph.Shape;
import gui.GuiSetup;
import markerDetection.MarkerDetectionSetup;
import markerDetection.MarkerObjectMap;
import markerDetection.UnrecognizedMarkerListener;
import system.EventManager;
//import util.IO;
//import util.Vec;
//import util.Wrapper;
//import worldData.MoveComp;
import worldData.Obj;
import worldData.SystemUpdater;
import worldData.World;
import actions.ActionBufferedCameraAR;
import android.app.Activity;
import de.rwth.GDXConnection;

public class OwnMarkerRenderSetup extends MarkerDetectionSetup {

    private GLCamera camera;
    private World world;
    public static MeshComponent pizzaMesh;
    private GL1Renderer renderer;

    // public Wrapper targetMoveWrapper = new Wrapper();

    @Override
    public UnrecognizedMarkerListener _a2_getUnrecognizedMarkerListener() {
	return null;
    }

    @Override
    public void _a3_registerMarkerObjects(MarkerObjectMap markerObjectMap) {
	markerObjectMap.put(new CameraMarker(0, camera));
    }

    @Override
    public void _a_initFieldsIfNecessary() {
	camera = new GLCamera();
	world = new World(camera);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void _b_addWorldsToRenderer(GL1Renderer renderer,
	    GLFactory objectFactory, GeoObj currentPosition) {
	this.renderer = renderer;
	this.renderer.addRenderElement(world);

	GDXConnection.init(myTargetActivity, renderer);

	new OwnModelLoader(this.renderer, "data/models/pizza_V2_4.obj",
		"data/models/pizzapepperoni4.jpg") {
	    @Override
	    public void modelLoaded(MeshComponent gdxMesh) {
		pizzaMesh = gdxMesh;
		final Obj o = new Obj();
		o.setComp(gdxMesh);
		world.add(o);
	    }
	};
    }

    @Override
    public void _c_addActionsToEvents(EventManager eventManager,
	    CustomGLSurfaceView arView, SystemUpdater updater) {
	arView.addOnTouchMoveListener(new ActionBufferedCameraAR(camera));

    }

    @Override
    public void _d_addElementsToUpdateThread(SystemUpdater updater) {
	updater.addObjectToUpdateCycle(world);

    }

    @Override
    public void _e2_addElementsToGuiSetup(GuiSetup guiSetup, Activity activity) {
    }

}

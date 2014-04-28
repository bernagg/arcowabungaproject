package org.escoladeltreball.arcowabungaproject;

import org.escoladeltreball.arcowabungaproject.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TabHost;

public class DishesMenu extends Activity implements OnTouchListener {
    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();

    TabHost tabs;
    float lastX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	// Remove title bar
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	setContentView(R.layout.activity_tabs);

	crearDatos();
	// Vista expandible
	ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
	Adaptador adapter = new Adaptador(this, grupos);
	listView.setAdapter(adapter);

	tabs = (TabHost) findViewById(android.R.id.tabhost);
	tabs.setup();

	TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
	spec.setContent(R.id.tab1);
	spec.setIndicator("TAB1");
	tabs.addTab(spec);

	spec = tabs.newTabSpec("mitab2");
	spec.setContent(R.id.tab2);
	spec.setIndicator("TAB2");
	tabs.addTab(spec);

	spec = tabs.newTabSpec("mitab3");
	spec.setContent(R.id.tab3);
	spec.setIndicator("TAB3");
	tabs.addTab(spec);

	tabs.setCurrentTab(0);

	// añadiendo la posibilidad de arrastrar a la derecha tambien en los
	// botones
	
	//De momento solo hay una listview en la tab1 (listViewexp)
	ExpandableListView elv = (ExpandableListView) findViewById(R.id.listViewexp);
	elv.setOnTouchListener(this);

    }

    /**
     * ESTE CREAR DATOS DEBE SER SUBSTITUIDO POR LA CARGA DE DATOS DE BASE DE DATOS
     */
    public void crearDatos() {

	GrupoDeItems grupo0 = new GrupoDeItems("Lechon");
	grupo0.children.add("Al horno");
	// grupo0.children.add("A la parrilla");
	grupos.append(0, grupo0);

	GrupoDeItems grupo1 = new GrupoDeItems("Pescado");
	grupo1.children.add("Paella");
	// grupo1.children.add("A la parrilla");
	// grupo1.children.add("Frito");
	grupos.append(1, grupo1);

	GrupoDeItems grupo2 = new GrupoDeItems("Sandwichs");
	grupo2.children.add("Jam�n, queso y anan�");
	// grupo2.children.add("Pollo, morrones y aceitunas");
	// grupo2.children.add("Carlitos");
	grupos.append(2, grupo2);
    }

    public void switchTabs(boolean direction) {

	if (direction) // true = move left
	{
	    if (tabs.getCurrentTab() == 0)
		tabs.setCurrentTab(tabs.getTabWidget().getTabCount() - 1);
	    else
		tabs.setCurrentTab(tabs.getCurrentTab() - 1);
	} else
	// move right
	{
	    if (tabs.getCurrentTab() != (tabs.getTabWidget().getTabCount() - 1))
		tabs.setCurrentTab(tabs.getCurrentTab() + 1);
	    else
		tabs.setCurrentTab(0);
	}
    }

    //El onTouchEvent viene de serie con la Activity
    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
	return touchController(touchevent);
    }
    //He añadido un onTouch para cuando se toque la lista expandible (cualquiera de sus elementos)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
	return touchController(event);
    }

    private boolean touchController(MotionEvent event) {
	switch (event.getAction()) {
	// when user first touches the screen to swap
	case MotionEvent.ACTION_DOWN: {
	    lastX = event.getRawX();
	    break;
	}
	case MotionEvent.ACTION_UP: {
	    float currentX = event.getRawX();
	    // if left to right swipe on screen
	    if (lastX < currentX) {

		switchTabs(true);
	    }
	    // if right to left swipe on screen
	    if (lastX > currentX) {
		switchTabs(false);
	    }

	    break;
	}
	}
	return false;
    }

}

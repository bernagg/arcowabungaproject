package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener{
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// Remove title bar
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	super.onCreate(savedInstanceState);
	// set content view AFTER ABOVE sequence (to avoid crash)
	this.setContentView(R.layout.activity_main);
	
	
	
	//Buttons
	LinearLayout bCarta = (LinearLayout) findViewById(R.id.linearButtonMenu);
	bCarta.setOnClickListener(this);
	
	LinearLayout bOrder = (LinearLayout) findViewById(R.id.linearButtonPedidoActual);
	bOrder.setOnClickListener(this);
	
	LinearLayout bLastOrders = (LinearLayout) findViewById(R.id.linearButtonPedidosAnteriores);
	bLastOrders.setOnClickListener(this);
	
	LinearLayout bContact = (LinearLayout) findViewById(R.id.linearButtonContacto);
	bContact.setOnClickListener(this);
	
	this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    @Override
    public void onClick(View v) {
	
	if ( v.getId() == R.id.linearButtonMenu ){
	    Intent intent = new Intent(this.getApplicationContext(), DishesMenuActivity.class);
       	    startActivity(intent);
	}else if ( v.getId() == R.id.linearButtonPedidoActual ){
	    Intent intent = new Intent(this.getApplicationContext(), OrderActivity.class);
       	    startActivity(intent);
	}else if ( v.getId() == R.id.linearButtonPedidosAnteriores ){
	    Intent intent = new Intent(this.getApplicationContext(), LastOrdersActivity.class);
       	    startActivity(intent);
	}else if ( v.getId() == R.id.linearButtonContacto ){
	    Intent intent = new Intent(this.getApplicationContext(), ContactActivity.class);
       	    startActivity(intent);
	}
	
    }

}
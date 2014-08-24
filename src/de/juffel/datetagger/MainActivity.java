package de.juffel.datetagger;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements
		ListView.OnItemClickListener {

	private String[] drawerItems = { "Main", "Tag List" };
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initialize drawer list
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		// set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, drawerItems));
		// set the lists click listener
		mDrawerList.setOnItemClickListener(this);

	}

	public MainActivity() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Method gets called from the DrawerItemClickListener, when the user clicks
	 * in the drawer
	 * 
	 * @param position
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		// build new fragment according to clicked item
		Fragment newFragment;
		switch (position) {
		case 1:
			newFragment = new HomeFragment();
			break;
		case 2:
			newFragment = new TagListFragment();
			break;
		default:
			return;
		}
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.fragment_container, newFragment);
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();

		// TODO init according fragment
		mDrawerLayout.closeDrawer(mDrawerList);
	}

}

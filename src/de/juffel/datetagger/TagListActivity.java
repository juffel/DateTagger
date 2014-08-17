package de.juffel.datetagger;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.juffel.datetagger.DateTaggerContract.TagEntry;

public class TagListActivity extends Activity {

	private List<TagEntry> tagList = new ArrayList<TagEntry>();
    private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_list);
		
		// set adapter for listview
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(new ArrayAdapter(this, R.layout.tag_list_item, tagList));
		// TODO set clicklistener
		updateTagList();
	}
	
    private void updateTagList() {
    	List<TagEntry> res = new ArrayList<TagEntry>();
    	String[] columns = { TagEntry.COLUMN_NAME_ID, TagEntry.COLUMN_NAME_CONTENT };
    	Cursor cursor = db.query(TagEntry.TABLE_NAME,
    			columns,
    			null,
    			null,
    			null,
    			null,
    			null);
    	
    	List<String> ret = new ArrayList<String>();

    	cursor.moveToFirst();
    	while (!cursor.isLast()) {
    		String content = cursor.getString(
    		    cursor.getColumnIndexOrThrow(TagEntry.COLUMN_NAME_CONTENT));
    		// String date = cursor.getString(cursor.getColumnIndexOrThrow(TagEntry.COLUMN_NAME_DATE));
    		// only add content for now
    		ret.add(content);
    		cursor.moveToNext();
    	}
    	tagList = res;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_list, menu);
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
}

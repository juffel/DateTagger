package de.juffel.datetagger;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.juffel.datetagger.DateTaggerContract.TagEntry;

public class TagListFragment extends Fragment {
	

	private List<TagEntry> tagList = new ArrayList<TagEntry>();
    private SQLiteDatabase db;

	/**
	 * This method gets called when instantiating the Fragment and is responsible
	 * for the layout of the fragment. This layout is defined via the returned
	 * view.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {

		// set adapter for listview
		ListView list = (ListView) getView().findViewById(R.id.listView1);
		list.setAdapter(new ArrayAdapter(getActivity(), R.layout.tag_list_item, tagList));
		// TODO set clicklistener

		updateTagList();

        return inflater.inflate(R.layout.fragment_home, container, false);
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

}

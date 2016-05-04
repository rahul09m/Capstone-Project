package com.rahulm09.android.smartcards.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Rahul on 01/05/2016.
 */
@Database(version = CardDatabase.VERSION)
public class CardDatabase {
    public static final int VERSION = 1;

    @Table(CardColumns.class) public static final String CARDS = "cards";
}

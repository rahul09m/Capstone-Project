package com.rahulm09.android.smartcards.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by Rahul on 01/05/2016.
 */
public interface CardColumns {
    @DataType(DataType.Type.INTEGER) @PrimaryKey
    @AutoIncrement
    String _ID = "_id";

   /*
  @DataType(DataType.Type.TEXT) @NotNull
  String CARD_ID = "card_id";
  */

    @DataType(DataType.Type.TEXT) @NotNull String NAME = "name";
    @DataType(DataType.Type.TEXT) @NotNull String NUMBER = "number";
    @DataType(DataType.Type.TEXT) String FORMAT = "format";
}

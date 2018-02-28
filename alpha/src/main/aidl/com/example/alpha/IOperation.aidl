package com.example.alpha;

interface IOperation {

   long insert(in ContentValues values);

   int delete(String selection, in List<String> selectionArgs);

   int update(in ContentValues values, String selection, in List<String> selectionArgs);

//   Cursor query(in List<String> projection, String selection, in List<String> selectionArgs, String sortOrder);

   String getTableName();

   String getTableIdFieldName();

}

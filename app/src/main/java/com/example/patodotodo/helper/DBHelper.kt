package com.example.patodotodo.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.patodotodo.model.Model_Category
import com.example.patodotodo.model.Model_Task
import com.example.patodotodo.model.Model_User

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object{
        private val DATABASE_VER = 1;
        private val DATABASE_NAME = "Patodotodo.db"

        private val TABLE_USER = "user"
        private val COL_ID_USER = "id_user"
        private val COL_NAME = "name_user"
        private val COL_EMAIL = "email_user"
        private val COL_USERNAME = "username_user"
        private val COL_PASSWORD = "password_user"

        private val TABLE_CATEGORY = "category"
        private val COL_ID_CATEGORY = "id_category"
        private val COL_NAME_CATEGORY = "name_category"

        private val TABLE_TASK = "task"
        private val COL_ID_TASK = "id_task"
        private val COL_NAME_TASK = "name_task"
        private val COL_DATE_TASK = "date_task"
        private val COL_TIME_TASK = "time_task"
        private val COL_ACTIVE = "task_active"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY1 = ("CREATE TABLE $TABLE_USER($COL_ID_USER INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, $COL_NAME TEXT, $COL_EMAIL TEXT, $COL_USERNAME TEXT, $COL_PASSWORD TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY1)

        val CREATE_TABLE_QUERY2 = ("CREATE TABLE $TABLE_CATEGORY($COL_ID_CATEGORY INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, $COL_ID_USER INT, $COL_NAME_CATEGORY TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY2)

        val CREATE_TABLE_QUERY3 = ("CREATE TABLE $TABLE_TASK($COL_ID_TASK INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, $COL_ID_USER INT, $COL_ID_CATEGORY INT, $COL_NAME_TASK TEXT, $COL_DATE_TASK TEXT, $COL_TIME_TASK TEXT, $COL_ACTIVE INT)")
        db!!.execSQL(CREATE_TABLE_QUERY3)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORY")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_TASK")
        onCreate(db!!)
    }

    val getAllUser:List<Model_User> get(){
        val listUser = ArrayList<Model_User>()
        val selectQuery = "SELECT * FROM $TABLE_USER"
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val user = Model_User()
                user.id_user = crusor.getInt(crusor.getColumnIndex(COL_ID_USER))
                user.name_user = crusor.getString(crusor.getColumnIndex(COL_NAME))
                user.email_user = crusor.getString(crusor.getColumnIndex(COL_EMAIL))
                user.username_user = crusor.getString(crusor.getColumnIndex(COL_USERNAME))
                user.password_user = crusor.getString(crusor.getColumnIndex(COL_PASSWORD))
                listUser.add(user)
            } while (crusor.moveToNext())
        }
        return listUser
    }

    fun getUser(id_user: Int):List<Model_User> {
        val listUser = ArrayList<Model_User>()
        val selectQuery = "SELECT * FROM $TABLE_USER WHERE $COL_ID_USER="+id_user
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val user = Model_User()
                user.id_user = crusor.getInt(crusor.getColumnIndex(COL_ID_USER))
                user.name_user = crusor.getString(crusor.getColumnIndex(COL_NAME))
                user.email_user = crusor.getString(crusor.getColumnIndex(COL_EMAIL))
                user.username_user = crusor.getString(crusor.getColumnIndex(COL_USERNAME))
                user.password_user = crusor.getString(crusor.getColumnIndex(COL_PASSWORD))
                listUser.add(user)
            } while (crusor.moveToNext())
        }
        return listUser
    }

    fun userValidation(email: String, username: String): Boolean{
        var listUser = ArrayList<Model_User>()
        val selectQuery = "SELECT * FROM $TABLE_USER WHERE $COL_EMAIL='"+email+"' OR $COL_USERNAME='"+username+"'"
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val user = Model_User()
                user.id_user = crusor.getInt(crusor.getColumnIndex(COL_ID_USER))
                user.name_user = crusor.getString(crusor.getColumnIndex(COL_NAME))
                user.email_user = crusor.getString(crusor.getColumnIndex(COL_EMAIL))
                user.username_user = crusor.getString(crusor.getColumnIndex(COL_USERNAME))
                user.password_user = crusor.getString(crusor.getColumnIndex(COL_PASSWORD))
                listUser.add(user)
            } while (crusor.moveToNext())
        }

        return listUser.size == 0
    }


    fun getUserLogin(username: String, password: String):List<Model_User> {
        val listUser = ArrayList<Model_User>()
        val selectQuery = "SELECT * FROM $TABLE_USER WHERE $COL_USERNAME='"+username+"' AND $COL_PASSWORD='"+password+"'"
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val user = Model_User()
                user.id_user = crusor.getInt(crusor.getColumnIndex(COL_ID_USER))
                user.name_user = crusor.getString(crusor.getColumnIndex(COL_NAME))
                user.email_user = crusor.getString(crusor.getColumnIndex(COL_EMAIL))
                user.username_user = crusor.getString(crusor.getColumnIndex(COL_USERNAME))
                user.password_user = crusor.getString(crusor.getColumnIndex(COL_PASSWORD))
                listUser.add(user)
            } while (crusor.moveToNext())
        }
        return listUser
    }

    fun addUser(user: Model_User){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, user.name_user)
        values.put(COL_EMAIL, user.email_user)
        values.put(COL_USERNAME, user.username_user)
        values.put(COL_PASSWORD, user.password_user)
        db.insert(TABLE_USER,null, values)
        db.close()
    }

    fun getAllCategory(id_user: Int): List<Model_Category>{
        val listCategory = ArrayList<Model_Category>()
        val selectQuery = "SELECT * FROM $TABLE_CATEGORY WHERE id_user="+id_user
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val category = Model_Category()
                category.id_category = crusor.getInt(crusor.getColumnIndex(COL_ID_CATEGORY))
                category.id_user = crusor.getInt(crusor.getColumnIndex(COL_ID_USER))
                category.name_category = crusor.getString(crusor.getColumnIndex(COL_NAME_CATEGORY))
                listCategory.add(category)
            } while (crusor.moveToNext())
        }
        return listCategory
    }

    fun getAllCategoryy(): List<Model_Category>{
        val listCategory = ArrayList<Model_Category>()
        val selectQuery = "SELECT * FROM $TABLE_CATEGORY"
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val category = Model_Category()
                category.id_category = crusor.getInt(crusor.getColumnIndex(COL_ID_CATEGORY))
                category.id_user = crusor.getInt(crusor.getColumnIndex(COL_ID_USER))
                category.name_category = crusor.getString(crusor.getColumnIndex(COL_NAME_CATEGORY))
                listCategory.add(category)
            } while (crusor.moveToNext())
        }
        return listCategory
    }

    fun allCategoryWithTask(id_user: Int): List<Model_Category>{
        val listCategory = ArrayList<Model_Category>()
        val selectQuery = "SELECT category.id_category, category.name_category, COUNT(task.id_task) AS jumlah FROM category LEFT JOIN task ON task.id_category=category.id_category WHERE category.id_user = "+id_user+" GROUP BY Category.name_category"
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val category = Model_Category()
                category.id_category = crusor.getInt(crusor.getColumnIndex(COL_ID_CATEGORY))
                category.name_category = crusor.getString(crusor.getColumnIndex(COL_NAME_CATEGORY))
                category.jumlahTask = crusor.getInt(crusor.getColumnIndex("jumlah"))
                listCategory.add(category)
            } while (crusor.moveToNext())
        }
        return listCategory
    }

    fun addCategory(category: Model_Category){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID_USER, category.id_user)
        values.put(COL_NAME_CATEGORY, category.name_category)
        db.insert(TABLE_CATEGORY,null, values)
        db.close()
    }

    fun updateCategory(category: Model_Category):Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID_USER, category.id_user)
        values.put(COL_NAME_CATEGORY, category.name_category)
        return db.update(TABLE_CATEGORY, values, "$COL_ID_CATEGORY=?",
            arrayOf(category.id_category.toString()))
    }

    fun deleteCategory(category: Model_Category){
        val db = this.writableDatabase
        db.delete(TABLE_CATEGORY,"$COL_ID_CATEGORY=?", arrayOf(category.id_category.toString()))
        db.close()
    }

    fun allTask(id_user: Int):List<Model_Task>{
        val listTask = ArrayList<Model_Task>()
        val selectQuery = "SELECT * FROM $TABLE_TASK WHERE id_user="+id_user
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val task = Model_Task()
                task.id_task = crusor.getInt(crusor.getColumnIndex(COL_ID_TASK))
                task.id_user = crusor.getInt(crusor.getColumnIndex(COL_ID_USER))
                task.id_category = crusor.getInt(crusor.getColumnIndex(COL_ID_CATEGORY))
                task.name_task = crusor.getString(crusor.getColumnIndex(COL_NAME_TASK))
                task.date_task = crusor.getString(crusor.getColumnIndex(COL_DATE_TASK))
                task.time_task = crusor.getString(crusor.getColumnIndex(COL_TIME_TASK))
                task.task_active = crusor.getInt(crusor.getColumnIndex(COL_ACTIVE))
                listTask.add(task)
            } while (crusor.moveToNext())
        }
        return listTask
    }

    fun getTask(id_task: Int):List<Model_Task>{
        val listTask = ArrayList<Model_Task>()
        val selectQuery = "SELECT * FROM $TABLE_TASK WHERE id_task="+id_task
        val db = this.writableDatabase
        val crusor = db.rawQuery(selectQuery, null)
        if(crusor.moveToFirst()){
            do {
                val task = Model_Task()
                task.id_task = crusor.getInt(crusor.getColumnIndex(COL_ID_TASK))
                task.id_user = crusor.getInt(crusor.getColumnIndex(COL_ID_USER))
                task.id_category = crusor.getInt(crusor.getColumnIndex(COL_ID_CATEGORY))
                task.name_task = crusor.getString(crusor.getColumnIndex(COL_NAME_TASK))
                task.date_task = crusor.getString(crusor.getColumnIndex(COL_DATE_TASK))
                task.time_task = crusor.getString(crusor.getColumnIndex(COL_TIME_TASK))
                task.task_active = crusor.getInt(crusor.getColumnIndex(COL_ACTIVE))
                listTask.add(task)
            } while (crusor.moveToNext())
        }
        return listTask
    }

    fun addTask(task: Model_Task){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COL_ID_CATEGORY, task.id_category)
        values.put(COL_NAME_TASK, task.name_task)
        values.put(COL_DATE_TASK, task.date_task)
        values.put(COL_TIME_TASK, task.time_task)
        values.put(COL_ACTIVE, task.task_active)

        db.insert(TABLE_TASK,null, values)
        db.close()
    }

    fun deleteTask(task: Model_Task){
        val db = this.writableDatabase
        db.delete(TABLE_TASK,"$COL_ID_TASK=?", arrayOf(task.id_task.toString()))
        db.close()
    }

    fun updateTask(task: Model_Task):Int{
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COL_NAME_TASK, task.name_task)
        values.put(COL_DATE_TASK, task.date_task)
        values.put(COL_TIME_TASK, task.time_task)
        values.put(COL_ID_CATEGORY, task.id_category)
        values.put(COL_ACTIVE, task.task_active)

        return db.update(
            TABLE_TASK, values, "$COL_ID_TASK=?",
            arrayOf(task.id_task.toString()))
    }

    fun updateTaskActive(task: Model_Task):Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ACTIVE, task.task_active)
        return db.update(TABLE_TASK, values, "$COL_ID_TASK=?",
            arrayOf(task.id_task.toString()))
    }

}
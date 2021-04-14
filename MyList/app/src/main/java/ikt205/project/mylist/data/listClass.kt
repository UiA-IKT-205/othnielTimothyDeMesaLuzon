package ikt205.project.mylist.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TodoList(val title: String, val entries: MutableList<String>): Parcelable

package ikt205.project.mylist.data
import java.util.*
/* Maybe 'list' isn't the smartest naming convention...
*  What do we need in a 2Do list?
*  1. Entries
*  1.1 Should entries be a string or a list of contents?
*  2. Date of when the list was made? Updated?
*/

data class ListEntry(val item: String, val date: Date){

    override fun toString(): String {
        return "$item, $date"
    }
}
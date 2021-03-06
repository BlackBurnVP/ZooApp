package com.vitalii.zooapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.animal_view.view.*

class MainActivity : AppCompatActivity() {

    private var listOfAnimals = ArrayList<Animals>()
    private var adapter:AnimalsAdapter?=null
    private lateinit var mListView:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load animals to the list
        listOfAnimals.add(Animals("Babon","Some description",R.drawable.baboon,false))
        listOfAnimals.add(Animals("Buldog","Some description",R.drawable.bulldog,true))
        listOfAnimals.add(Animals("Panda","Some description",R.drawable.panda, false))
        listOfAnimals.add(Animals("Zebra","Some description",R.drawable.zebra, false))
        listOfAnimals.add(Animals("Swallow Bird","Some description",R.drawable.swallow_bird,false))
        listOfAnimals.add(Animals("Tiger","Some description",R.drawable.white_tiger,true))

        mListView = findViewById(R.id.lvAnimals)
        adapter = AnimalsAdapter(this,listOfAnimals)
        mListView.adapter = adapter
    }

    inner class AnimalsAdapter:BaseAdapter{
        constructor(context: Context?, listOfAnimals:ArrayList<Animals>){
            this.context = context
            this.listOfAnimals = listOfAnimals
        }

        /**
         * Delete clicked animal
         * @param index - position of clicked item
         */
        private fun deleteAnimal(index:Int){
            listOfAnimals.removeAt(index)
            adapter!!.notifyDataSetChanged()
        }
        /**
         * Duplicate clicked animal
         * @param index - position of clicked item
         */
        private fun addAnimal(index:Int){
            listOfAnimals.add(index,listOfAnimals[index])
            adapter!!.notifyDataSetChanged()
        }

        private var listOfAnimals = ArrayList<Animals>()
        private var context:Context? = null

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listOfAnimals[position]
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val myView = if (animal.isColor){
                inflater.inflate(R.layout.animal_killer_view, null)
            }else{
                inflater.inflate(R.layout.animal_view,   null)
            }
            myView.txt_AnimalName.text = animal.name
            myView.txt_ShortDesc.text = animal.des
            myView.animal_pic.setImageResource(animal.image)
            myView.animal_pic.setOnClickListener{
//                deleteAnimal(position)
//                addAnimal(position)
                val intent = Intent(context,AnimalInfo::class.java)
                intent.putExtra("name",animal.name)
                intent.putExtra("description",animal.des)
                intent.putExtra("image",animal.image)
                startActivity(intent)
        }
            return myView
        }

        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}

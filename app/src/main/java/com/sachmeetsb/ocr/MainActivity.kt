package com.sachmeetsb.ocr

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

import java.util.logging.Logger


class MainActivity : AppCompatActivity() {

    var srcBmp : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var seekValue: Int = 0
        val imageCol = findViewById<ImageView>(R.id.col)
        imageCol.setBackgroundColor(Color.rgb(12,12,12))
        val capture = findViewById<Button>(R.id.button)
        val shift = findViewById<Button>(R.id.button2)
        shift.visibility = View.GONE
        val seekBar :SeekBar = findViewById<SeekBar>(R.id.seekBar4)
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               // seekBar?.setProgress(progress)
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
                    // write custom code for progress is started
                }

                override fun onStopTrackingTouch(seek: SeekBar) {
                    // write custom code for progress is stopped
//                    Toast.makeText(this@MainActivity,
//                        "Progress is: " + seek.progress + "%",
//                        Toast.LENGTH_SHORT).show()

                    if(srcBmp!=null)
                        seek(seek.progress*10000)

                        imageCol.setBackgroundColor(seek.progress*10000)
                }
            })


//        shift.setOnClickListener{
//            shift(it)
//        }
        capture.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,17)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==17){
            srcBmp = data?.extras?.get("data") as Bitmap
//            val width: Int = srcBmp.getWidth()
//            val height: Int = srcBmp.getHeight()
//
//            val srcHSV = FloatArray(3)
//            val dstHSV = FloatArray(3)

//            val dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//
//            for (row in 0 until height) {
//                for (col in 0 until width) {
//                    val pixel: Int = srcBmp.getPixel(col, row)
//                    val alpha: Int = Color.alpha(pixel)
//                    Color.colorToHSV(pixel, srcHSV)
//
//                    Color.colorToHSV(697885, dstHSV)
//                    print(dstHSV)
//                    // If it area to be painted set only value of original image
//                    //Logger.getLogger(MainActivity::class.java.name).warning(pixel.toString())
//                    //dstHSV[0] = srcHSV[0]
//                    dstHSV[1] = srcHSV[1] // value
//                    dstHSV[2] = srcHSV[2]
//                    dstBitmap.setPixel(col, row, Color.HSVToColor(alpha, dstHSV))
//                }
//            }

            Snackbar.make(this.findViewById(android.R.id.content),"Move slider to see results",Snackbar.LENGTH_SHORT).show()

            imageView.setImageBitmap(srcBmp)


        }

    }


//    fun shift(view: View){
//
//        val red = findViewById<EditText>(R.id.RED).text.toString()
//        val green = findViewById<EditText>(R.id.GREEN).text.toString()
//        val blue = findViewById<EditText>(R.id.BLUE).text.toString()
//
//        if(red.matches(Regex("")) or blue.matches(Regex("")) or green.matches(Regex("")))
//        {
//            Snackbar.make(view,"Please Enter RGB Values",Snackbar.LENGTH_SHORT).show()
//            //Toast.makeText(this,"Please fill RGB Values", Toast.LENGTH_LONG).show()
//            return
//        }
//
//        val redInt = red.toInt()
//        val greenInt = green.toInt()
//        val blueInt = blue.toInt()
//
//
//        val width: Int = srcBmp.getWidth()
//        val height: Int = srcBmp.getHeight()
//
//        val srcHSV = FloatArray(3)
//        val dstHSV = FloatArray(3)
//
//        val dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//        Color.RGBToHSV(red.toInt(),green.toInt(),blue.toInt() , dstHSV)
//
//        for (row in 0 until height) {
//            for (col in 0 until width) {
//                val pixel: Int = srcBmp.getPixel(col, row)
//                // val alpha: Int = Color.alpha(pixel)
//                Color.colorToHSV(pixel, srcHSV)
//                //print(dstHSV)
//                // If it area to be painted set only value of original image
//                //Logger.getLogger(MainActivity::class.java.name).warning(pixel.toString())
//                //dstHSV[0] = srcHSV[0]
//                dstHSV[1] = srcHSV[1] // value
//                dstHSV[2] = srcHSV[2]
//                dstBitmap.setPixel(col, row, Color.HSVToColor(dstHSV))
//            }
//        }
//
//
//
//        imageView.setImageBitmap(dstBitmap)
//
//
//    }
//

    fun seek(x: Int){


            val width: Int = srcBmp!!.getWidth()
            val height: Int = srcBmp!!.getHeight()

            val srcHSV = FloatArray(3)
            val dstHSV = FloatArray(3)

            val dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

            for (row in 0 until height) {
                for (col in 0 until width) {
                    val pixel: Int = srcBmp!!.getPixel(col, row)
                    val alpha: Int = Color.alpha(pixel)
                    Color.colorToHSV(pixel, srcHSV)

                    Color.colorToHSV(x, dstHSV)
                    //print(dstHSV)
                    // If it area to be painted set only value of original image
                    //Logger.getLogger(MainActivity::class.java.name).warning(pixel.toString())
                    //dstHSV[0] = srcHSV[0]
                    dstHSV[1] = srcHSV[1] // value
                    dstHSV[2] = srcHSV[2]
                    dstBitmap.setPixel(col, row, Color.HSVToColor(alpha, dstHSV))
                }
            }
        imageView.setImageBitmap(dstBitmap)
    }


}

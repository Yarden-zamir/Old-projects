package net.panda.ActionConsole.tools

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Animation | Templates
 * and open the template in the editor.
 */
import battleclick.settings
import com.sun.javafx.util.Utils
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.Image
import java.awt.Point
import java.awt.RenderingHints
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.image.BufferedImage
import java.io.File
import javax.swing.Icon
import javax.swing.ImageIcon
import javax.swing.Timer
import org.jdesktop.swingx.JXLabel

/**
 *
 * @author hrsid
 */
class Animation {

    var t: Timer? = null
    var moving = false//(float) Math.max(rangeX, -rangeX) + (float) Math.max(rangeY, -rangeY)

    fun resizeImgAnim(l: JXLabel, a: File, from: Dimension, to: Dimension, Time: Float) {
        val img = ImageIcon(a.absolutePath)
        val image = img.image // transform it

        val time = Time * settings.animationScale
        if (t != null) {
            t!!.stop()
        }
        moving = true
        t = Timer(0, object : ActionListener {
            internal var baseTime = System.currentTimeMillis()
            internal var last = System.currentTimeMillis() - baseTime
            internal var lastProg = 0f

            override fun actionPerformed(e: ActionEvent) {
                last = System.currentTimeMillis() - baseTime
                val rangeX = to.getWidth() - from.getWidth()
                val rangeY = to.getHeight() - from.getHeight()
                val prog = smootherstep(0f, time, last.toFloat())
                var x = (from.getWidth() + rangeX * prog).toInt()
                var y = (from.getHeight() + rangeY * prog).toInt()
                //                c.setSize(x, y);
                if (x == 0) {
                    x = 1
                    y = 1
                }
                var newimg = image.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH) // scale it the smooth way

                l.icon = ImageIcon(newimg)
                l.setSize(x, y)
                lastProg = prog
                if (prog >= 1) {
                    //                    c.setSize(to);

                    newimg = image.getScaledInstance(to.width, to.height, java.awt.Image.SCALE_SMOOTH) // scale it the smooth way
                    l.icon = ImageIcon(newimg)
                    l.size = to
                    t!!.stop()
                }
            }
        })
        t!!.start()
    }

    fun resize(c: Component, from: Dimension, to: Dimension, Time: Float) {
        val time = Time * settings.animationScale
        if (t != null) {
            t!!.stop()
        }
        c.size = from
        moving = true
        t = Timer(0, object : ActionListener {
            internal var baseTime = System.currentTimeMillis()
            internal var last = System.currentTimeMillis() - baseTime
            internal var lastProg = 0f

            override fun actionPerformed(e: ActionEvent) {
                last = System.currentTimeMillis() - baseTime
                val rangeX = to.getWidth() - from.getWidth()
                val rangeY = to.getHeight() - from.getHeight()
                val prog = smootherstep(0f, time, last.toFloat())
                val x = (from.getWidth() + rangeX * prog).toInt()
                val y = (from.getHeight() + rangeY * prog).toInt()
                c.setSize(x, y)
                lastProg = prog
                if (prog >= 1) {
                    var temp = to
                    if (to.height == 1) {
                        temp = Dimension(0, 0)
                    }
                    c.size = temp
                    t!!.stop()
                }
            }
        })
        t!!.start()
    }

    fun move(c: Component, from: Point, to: Point, Time: Float) {
        if (t != null && t!!.isRunning) {
            t!!.stop()
            t = null
        }
        val time = Time * settings.animationScale
        if (t != null) {
            t!!.stop()
        }
        c.location = from
        moving = true
        t = Timer(0, object : ActionListener {
            internal var baseTime = System.currentTimeMillis()
            internal var last = System.currentTimeMillis() - baseTime
            internal var lastProg = 0f

            override fun actionPerformed(e: ActionEvent) {
                last = System.currentTimeMillis() - baseTime
                val rangeX = to.getX() - from.getX()
                val rangeY = to.getY() - from.getY()
                val prog = smootherstep(0f, time, last.toFloat())
                val x = (from.getX() + rangeX * prog).toInt()
                val y = (from.getY() + rangeY * prog).toInt()
                c.setLocation(x, y)
                lastProg = prog
                if (prog >= 1) {
                    c.location = to
                    t!!.stop()
                }

            }
        })
        t!!.start()
    }

    fun getScaledImage(srcImg: ImageIcon, w: Int, h: Int): BufferedImage {
        val resizedImg = BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)
        val g2 = resizedImg.createGraphics()

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
        g2.drawImage(srcImg.image, 0, 0, w, h, null)
        g2.dispose()

        return resizedImg
    }

    companion object {

        fun smoothstep(edge0: Float, edge1: Float, x: Float): Float {
            var x = x
            // Scale, bias and saturate x to 0..1 range
            x = Utils.clamp(((x - edge0) / (edge1 - edge0)).toDouble(), 0.0, 1.0).toFloat()
            // Evaluate polynomial
            return x * x * (3 - 2 * x)
        }

        fun smootherstep(edge0: Float, edge1: Float, x: Float): Float {
            var x = x
            // Scale, and clamp x to 0..1 range
            x = Utils.clamp(((x - edge0) / (edge1 - edge0)).toDouble(), 0.0, 1.0).toFloat()
            // Evaluate polynomial
            return x * x * x * (x * (x * 6 - 15) + 10)
        }
    }
}

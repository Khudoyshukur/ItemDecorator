package com.example.itemdecorator

    import android.content.Context
    import android.graphics.Canvas
    import android.graphics.Rect
    import android.graphics.drawable.Drawable
    import android.view.View
    import androidx.core.view.children
    import androidx.recyclerview.widget.RecyclerView
    import java.lang.IllegalArgumentException
    import kotlin.math.roundToInt

    /**
     * Created by: androdev
     * Date: 9/26/2021
     * Time: 5:17 AM
     * Email: Khudoyshukur.Juraev.001@mail.ru
     */


    /**
     * @param context -> Context to obtain the default divider drawable object
     * */

    class VerticalFirstDividerDecoration(context: Context) :
        RecyclerView.ItemDecoration() {

        private var mDivider: Drawable? = null
        private val mBounds = Rect()

        init {
            val typedArray = context.obtainStyledAttributes(ATTRS)
            mDivider = typedArray.getDrawable(0)
            typedArray.recycle()
        }

        /**
         * Sets drawable for this divider
         * @param drawable is used as a divider
         * */
        fun setDrawable(drawable: Drawable?) {
            if (drawable == null) {
                throw IllegalArgumentException("Drawable must not be null!")
            }
            mDivider = drawable
        }

        override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            if (parent.layoutManager == null ||
                mDivider == null
            ) {
                return
            }

            parent.children.firstOrNull()?.let {
                if (parent.getChildAdapterPosition(it) == 0) {
                    drawVerticalDivider(canvas, parent)
                }
            }
        }

        private fun drawVerticalDivider(canvas: Canvas, recyclerView: RecyclerView) {
            canvas.save()
            val left: Int
            val right: Int

            if (recyclerView.clipToPadding) {
                left = recyclerView.paddingLeft
                right = recyclerView.width - recyclerView.paddingRight
                canvas.clipRect(
                    left, recyclerView.paddingTop, right,
                    recyclerView.height - recyclerView.paddingBottom
                )
            } else {
                left = 0
                right = recyclerView.width
            }

            val child: View = recyclerView.getChildAt(0)
            recyclerView.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom: Int = mBounds.bottom + child.translationY.roundToInt()
            val top = bottom - mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(canvas)
            canvas.restore()
        }

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            if (mDivider == null || parent.getChildAdapterPosition(view) != 0) {
                outRect.set(0, 0, 0, 0)
                return
            }

            outRect.set(0, 0, 0, mDivider!!.intrinsicHeight)
        }

        companion object {
            private val ATTRS = intArrayOf(android.R.attr.listDivider)
        }
    }

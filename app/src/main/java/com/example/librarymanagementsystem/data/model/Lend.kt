package com.project.mylibrary.data

import java.util.*

/**
 * 封装了 每条借阅记录。
 */
data class Lend (
    val id: Int,
    val bookId: Int,
    val userId: String,
    val lendTime: Calendar,
    private var returnTime: Calendar, // 实际还书时间, 还书期限
    private var state: Boolean // 还书状态 (true 为已归还, false 为未归还)
) {

    /**
     * 如果读者在还书期限的前一周没有还书，则需要对其进行警告。
     */
    fun warning(current: Calendar): Boolean {
        val flag = current.after(returnTime.add(Calendar.DATE, -7))
        returnTime.add(Calendar.DATE, 7)
        return flag && !state
    }

    fun returnBook() {
        state = true
        returnTime = Calendar.getInstance()
    }

}

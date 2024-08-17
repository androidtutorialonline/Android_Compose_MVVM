package com.androidapps.composeMVVM.data.model.receivedEvents

data class Links(
    val comments: Comments? = Comments(),
    val commits: Commits? = Commits(),
    val html: Html? = Html(),
    val issue: Issue? = Issue(),
    val review_comment: ReviewComment? = ReviewComment(),
    val review_comments: ReviewComments? = ReviewComments(),
    val self: Self? = Self(),
    val statuses: Statuses = Statuses()
)
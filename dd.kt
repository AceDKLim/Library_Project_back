    private fun logoutAndNavigateToMainActivity() {
        // Coroutine을 사용하여 로그아웃 요청을 비동기적으로 처리
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // logout 호출
                userApi.logout()
            } catch (e: Exception) {
                // 오류 발생 시 메시지 표시
                runOnUiThread {
                    Toast.makeText(this@HomeActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
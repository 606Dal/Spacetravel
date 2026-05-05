$(document).ready(function() {
    
    $("#btnSaveCache").on("click", function() {
        if(!confirm("현재 나사 사진과 정보를 서버에 저장하시겠습니까?")) return;

        // 버튼 중복 클릭 방지 (비활성화)
        const $btn = $(this);
        $btn.prop("disabled", true).text("저장 중...");

        const csrfToken = $("meta[name='_csrf']").attr("content");
        const csrfHeader = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            url: '/infoview/saveApodCache',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(APOD_DATA), // HTML에서 선언한 전역 변수 사용
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function(response) {
				console.log("서버 응답 데이터:", response);
                if (response.status === 'SUCCESS') {
                    alert("성공적으로 서버에 저장되었습니다!");
                    $btn.text("저장 완료됨"); // 계속 비활성화
                    
                } else if (response.status === 'NOT_IMAGE') {
                    alert("동영상 등 이미지 이외의 매체는 저장할 수 없습니다.");
                    $btn.prop("disabled", false).text("💾 현재 정보 저장하기"); // 다시 누를 수 있게 복구
                    
                } else if (response.status === 'DUPLICATE') {
                    alert("이미 저장된 날짜입니다.");
                    $btn.text("이미 저장됨"); // 텍스트만 바꾸고 계속 비활성화
                    
                } else {
                    alert("알 수 없는 상태입니다.");
                    $btn.prop("disabled", false).text("💾 현재 정보 저장하기"); 
                }
            },
            error: function(xhr, status, error) {
				console.error("상태 코드:", xhr.status);
				console.error("에러 메시지:", error);
                alert("저장에 실패했습니다. 이미 저장된 날짜인지 확인해 주세요.");
                $btn.prop("disabled", false).text("💾 현재 정보 저장하기"); // 실패 시 원상복구
            }
        });
    });

});
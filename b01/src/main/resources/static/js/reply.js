// Axios를 이용할 때 async/await 를 같이 이용하면 비동기 처리를 동기화된 코드처럼 작성 가능
// async는 함수가 비동기 처리를 위한 함수라는 것을 명시하기 위해 사용
// await는 async 함수 내에서 비동기 호출하는 부분에서 사용

// 화면에서 결과가 필요하다면 Axios의 호출 결과를 반환받아야 함
// 비동기 함수 get1()
async function get1(bno) {

    const result = await axios.get(`/replies/list/${bno}`)

    //console.log(result)

    return result;
}


// async function getList({bno, page, size, goLast}){
//
//     const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})
//
//     return result.data
// }


// 댓글 페이징 처리
/*  bno : 현재 게시물 번호
    page : 페이지 번호
    size : 페이지당 사이즈
    goLast : 마지막 페이지 호출 여부 (댓글의 경우 한 페이지에서 모든 동작이 이루어지는데 새로 등록된 댓글은 마지막 페이지에 있기 때문에 강제적으로 마지막 댓글 페이지를 호출)  */
async function getList({bno, page, size, goLast}){

    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})

    if(goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))

        return getList({bno:bno, page:lastPage, size:size})

    }

    return result.data
}

// 새로운 댓글을 등록하는 기능
// 파라미터를 JS의 객체로 받아서 axios.post()를 이용해서 전달
// addReply()가 정상적으로 처리되면 서버에서는 '{'rno':11}'과 같은 JSON 데이터를 전송
async function addReply(replyObj) {
    const response = await axios.post(`/replies/`,replyObj)
    return response.data
}

// 특정한 번호의 댓글을 조회하고 수정하는 기능
// 댓글 조회는 GET 방식으로, 댓글 수정은 PUT 방식으로 호출
async function getReply(rno) {
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

async function modifyReply(replyObj) {

    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj)
    return response.data
}

// 댓글의 삭제 처리
async function removeReply(rno) {
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}
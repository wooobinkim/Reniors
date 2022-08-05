const HOST = 'http://localhost:8000/api/v1'

const USER = '/users'
const RESUME = '/resume'
const COMPANY = '/company'
const EVAL = '/eval'
const JOB = '/jobopening'
const BOARD = '/boards'
const ROOM = '/rooms'
const CATEGORY = '/categories'
const PRACTICE = '/practice'
const QEUSTION = '/questions'

export default {
  user: {
    signup: () => HOST + USER,
    login: () => HOST + USER,
    kakaoSignup: () => HOST + USER + '/kakao',
    kakaoLogin: () => HOST + USER + '/kakao',
    userDetail: userId => HOST + USER + `/${userId}`,
    // id찾기, 비밀번호 찾기 이해를 못하겠음
    //
    userList: () => HOST + USER + '/list',
    bookmark: userId => HOST + USER + `/${userId}` + '/bookmark',
    bookmarkEdit: (userId, jobOpeningId) => HOST + USER + `/${userId}` + '/bookmark' + `/${jobOpeningId}`,
    hopearea: userId => HOST + USER + `/${userId}` + '/hopearea',
    hopeareaEdit: (userId, gugunId) => HOST + USER + `/${userId}` + '/hopearea' + `/${gugunId}`,
    jobinterest: userId => HOST + USER + `/${userId}` + '/jobinterest',
    jobinterestEdit: (userId, jobSmallCategoryId) => HOST + USER + `/${userId}` + 'jobinterest' + `/${jobSmallCategoryId}`,
    recruithistory: userId => HOST + USER + `/${userId}` + '/recruithistory',
  },
  resume: {
    get: userId => HOST + RESUME + `/${userId}`,
    edit: userId => HOST + RESUME + `/${userId}`,
    career: resumeId => HOST + RESUME + `/${resumeId}` + '/career',
    // careerEdit: (resumeId, careerDatailId) => HOST + RESUME + `/${resumeId}` + '/career' + `/${careerDetailId}`,
    awards: resumeId => HOST + RESUME + `/${resumeId}` + '/awards',
    awardsEdit: (resumeId, awardCareerId) => HOST + RESUME + `/${resumeId}` + '/awards' + `${awardCareerId}`,
    license: resumeId => HOST + RESUME + `/${resumeId}` + '/license',
    licenseEdit: (resumeId, licenseId) => HOST + RESUME + `/${resumeId}` + '/license' + `/${licenseId}`,
    portfolio: resumeId => HOST + RESUME + `/${resumeId}` + 'portfolio',
    // portfolio: (resumeId, portfolioName) => HOST + RESUME + `/${resumeId}` + 'portfolio' + `/${portfolioName}`,
  },
  company: {
    signup: () => HOST + COMPANY,
    login: () => HOST + COMPANY + '/login',
    detail: () => HOST + COMPANY,
    jobopening: () => HOST + COMPANY + '/jobopening',
    jobopeningEdit: jobOpeningId => HOST + COMPANY + '/jobopening' + `/${jobOpeningId}`,
    jobopeningApplicant: jobOpeningId => HOST + COMPANY + '/jobopening' + `/${jobOpeningId}` + '/applicant',
    jobopeningApplicantEdit: (jobOpeningId, applicantId) => HOST + COMPANY + '/jobopening' + `/${jobOpeningId}` + '/applicant' + `/${applicantId}`,
  },
  eval: {
    new: () => HOST + EVAL,
    get: () => HOST + EVAL,
    detail: evalId => HOST + EVAL + `/${evalId}`,
    question: evalId => HOST + EVAL + `/${evalId}` + '/question', 
    questionEdit: (evalId, evalQuestionId) => HOST + EVAL + `/${evalId}` + '/question' + `/${evalQuestionId}`, 
    questionReview: (evalId, evalQuestionId) => HOST + EVAL + `/${evalId}` + '/question' + `/${evalQuestionId}` + '/user', 
    questionReviewDelete: (evalId, evalQuestionId, userEvalId) => HOST + EVAL + `/${evalId}` + '/question' + `/${evalQuestionId}` + '/user' + `/${userEvalId}`, 
  },
  jobopening: {
    get: () => HOST + JOB,
    detail: jobOpeningId => HOST + JOB + `/${jobOpeningId}`,
    bookmark: jobOpeningId => HOST + JOB + `/${jobOpeningId}` + '/bookmark',
    applicant: jobOpeningId => HOST + JOB + `/${jobOpeningId}` + '/applicant',
  },
  board: {
    get: jobParentCategoryId => HOST + BOARD + '/categories' + `/${jobParentCategoryId}`,
    new: jobParentCategoryId => HOST + BOARD + '/categories' + `/${jobParentCategoryId}`,
    detail: jobBoardId => HOST + BOARD + `/${jobBoardId}`,
    // jobBoardId를 중복해서 쓰길래, 뒤에꺼 안씀
    comment: jobBoardId => HOST + BOARD + `/${jobBoardId}` + '/comments',
    commentEdit: (jobBoardId, jobBoardCommentId) => HOST + BOARD + `/${jobBoardId}` + '/comments' + `/${jobBoardCommentId}`,
  },
  room: {
    get: companyId => HOST + ROOM + `/${companyId}`,
    edit: roomId => HOST + ROOM + `/${roomId}`,
    users: roomId => HOST + ROOM + `/${roomId}` + '/users',
    // evalhistory: roomId => HOST + ROOM + '/evalhistory',
  },
  category: {
    sido: () => HOST + CATEGORY + '/sido',
    sidoEdit: (sidoId) => HOST + CATEGORY + '/sido' + `/${sidoId}`,
    gugun: (sidoId) => HOST + CATEGORY + '/sido' + `/${sidoId}` + '/gugun',
    gugunEdit: (sidoId, gugunId) => HOST + CATEGORY + '/sido' + `/${sidoId}` + '/gugun' + `/${gugunId}`,
    jobs: () => HOST + CATEGORY + '/jobs',
    jobsCategory: (jobParentsCategoryid) => HOST + CATEGORY + '/jobs' + `/${jobParentsCategoryid}`,
  },
  practice: {
    question: (interviewQuestionCategory) => HOST + PRACTICE + '/question' + `/${interviewQuestionCategory}`,
    answer: (interviewQuestionCategory) => HOST + PRACTICE + '/question' + `/${interviewQuestionCategory}` + '/answer',
    answerEdit: (interviewQuestionCategory, interviewQuestionAnswerId) => HOST + PRACTICE + '/question' + `/${interviewQuestionCategory}` + '/answer' + `/${interviewQuestionAnswerId}`,
    questionCategory: () => HOST + PRACTICE + '/questioncategories',
    video: (userId) => HOST + PRACTICE + '/list' + `/${userId}`,
    videoDetail: (practiceInterviewId) => HOST + PRACTICE + `/${practiceInterviewId}`,
  },
  question: {
    get: () => HOST + QEUSTION,
    new: () => HOST + QEUSTION,
    edit: (questionId) => HOST + QEUSTION + `${questionId}`,
    answerPost: (questionId) => HOST + QEUSTION + `${questionId}` + 'answers',
    answer: (questionId, answerId) => HOST + QEUSTION + `${questionId}` + 'answers' + `${answerId}`,

  }
}

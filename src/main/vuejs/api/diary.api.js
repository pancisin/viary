import Vue from 'vue'

const DIARY_API_URL = '/api/v1/diary';

export default {

  getDiary(diaryId, success) {
    Vue.http.get(`${DIARY_API_URL}/${diaryId}`).then(response => {
      success(response.body);
    })
  },

  getDays(diaryId, filters, success) {
    Vue.http.get(`${DIARY_API_URL}/${diaryId}/day`, {
      params: {
        from: filter.from || null,
        to: filter.to || null
      }
    }).then(response => {
      success(response.body)
    })
  },

  postDay(diaryId, dateNumber, year, content, success) {
    Vue.http.post(`${DIARY_API_URL}/${diaryId}/day/${dateNumber}/${year}`, {
      content
    }).then(response => {
      success(response.body)
    })
  }
}
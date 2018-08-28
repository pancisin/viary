import Vue from 'vue'

const DIARY_API_URL = '/api/v1/diary';

export default {

  getDiary(diaryId, success) {
    Vue.http.get(`${DIARY_API_URL}/${diaryId}`).then(response => {
      success(response.body);
    })
  },

  getDays(diaryId, filter, success) {
    Vue.http.get(`${DIARY_API_URL}/${diaryId}/day`, {
      params: {
        from: filter.from || null,
        to: filter.to || null
      }
    }).then(response => {
      success(response.body)
    })
  },

  postDay(diaryId, { date_number, year, content }, success) {
    Vue.http.post(`${DIARY_API_URL}/${diaryId}/day/${date_number}/${year}`, {
      content
    }).then(response => {
      success(response.body)
    })
  }
}
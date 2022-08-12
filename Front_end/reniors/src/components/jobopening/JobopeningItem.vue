<template>
  <router-link class="jobopening-item" :to="{ name: 'JobopeningDetail', params: { jobopeningId: jobopening.id } }">
    <div>
      <p class="jobopening-item-company">{{ jobopening.companyName }}</p>
      <p class="jobopening-item-title">{{ jobopening.title }}</p>
    </div>
    <p class="jobopening-item-period">{{ createDate }} ~ <br>{{ finishedDate }}</p>
  </router-link>
</template>

<script>
export default {
  name: 'JobopeningItem',
  props: {
    jobopening: Object,
  },
  setup(props) {
    const stringToDate = (rawDate) => {
      let dateComponents = rawDate.split('T');
      let datePieces = dateComponents[0].split("-");
      return (new Date(datePieces[0], (datePieces[1] - 1), datePieces[2]))
    }
    // toStringByFormatting
    function tSBF(source) {
      function leftPad(value) {
        if (value >= 10) {
          return value;
        }

        return `0${value}`;
      }
      const days = [
        '일',
        '월',
        '화',
        '수',
        '목',
        '금',
        '토'
      ]
      const year = source.getFullYear();
      const month = leftPad(source.getMonth() + 1);
      const day = leftPad(source.getDate());
      const dayName = days[source.getDay()]
      return [year, month, day].join('-') + ` (${dayName})`;
    }
    const createDate = tSBF(stringToDate(props.jobopening?.createdDate))
    const finishedDate = tSBF(stringToDate(props.jobopening?.finishedDate))

    return {
      createDate, finishedDate,
    }
  },
}
</script>

<style>
.jobopening-item {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
  background-color: white;
  border-radius: 0.4rem;
  margin: 10px 10px;
  padding: 10px;
  height: 150px;
  text-decoration: none;
}

.jobopening-item p {
  margin: 0;
  text-align: start;
}

.jobopening-item-company, .jobopening-item-title {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  -webkit-line-clamp: 1;
  color: black;
}

.jobopening-item-title {
  text-decoration-line: underline;
  -webkit-line-clamp: 3;
  font-weight: bold;
}

.jobopening-item-period {
  margin-top: 10px;
  color: var(--color-green-1);
  font-size: 10px;
}
</style>
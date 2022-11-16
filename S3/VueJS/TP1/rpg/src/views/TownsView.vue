<template>
  <v-container>
    <h1>Les villes</h1>
    {{ villes }}
  </v-container>
</template>

<script>

import {mapState} from 'vuex'
export default {
  name: 'TownsView',
  data: () => ({

  }),
  computed: {
    ...mapState(['villes'])
  }

}
</script>

<template>
  <v-container>
    <h1>Les personnages</h1>
    {{ persos }}
  </v-container>

</template>

<script>

import {mapState} from "vuex";

export default {
  name: 'PersosView',
  data: () => ({

  }),
  computed: {
    ...mapState(['persos'])
  }
}
</script>

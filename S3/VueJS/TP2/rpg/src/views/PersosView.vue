<template>
  <v-container>
    <v-row>
      <v-col class="col-2">
        <h1>Les personnages</h1>
        <select v-model="persoChoiced" id="persos">
          <option v-for="(perso, index) in persos" :key="index">{{perso.nom}}</option>
        </select>
      </v-col>
      <v-col v-if="persoChoiced !== ''" class="col-10" >
        {{perso_choisi}}
        <v-simple-table>
          <thead>
          <tr>
            <th>Attributs</th>
            <th>Emplacements</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>
              <ul>
                <li>niveau : {{perso_choisi.niveau}}</li>
                <li v-for="(attr, key, index) in perso_choisi.attributs" :key="index">{{key}} : {{attr}}</li>
              </ul>
            </td>

            <td>
              <ul>
                <li v-for="(emplacement, index) in perso_choisi.emplacements" :key="index">
                  {{emplacement.nom}}[{{emplacement.items.length}}] :  {{emplacement.items}}
                </li>
              </ul>
            </td>
          </tr>
          <tr></tr>
          </tbody>
        </v-simple-table>
      </v-col>
    </v-row>
  </v-container>

</template>

<script>

import {mapState} from "vuex";

export default {
  name: 'PersosView',
  data: () => ({
    persoChoiced : '',
  }),
  computed: {
    ...mapState(['persos']),
    perso_choisi() {
      return this.persos.find(p => p.nom === this.persoChoiced)
    }
  }
}
</script>

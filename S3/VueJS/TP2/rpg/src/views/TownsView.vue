<template>
  <v-container>
    <v-row>
      <v-col class="col-2">

        <div>
          <h1>Les villes</h1>
          <label for="filteractive">filtrage possible : </label>
          <input type="checkbox" v-model="filterActive" id="filteractive">
          <div v-if="filterActive">
            <v-text-field v-model="filter"></v-text-field>
<!--            <label for="filtertown">filtre : </label><input v-model="filter" id="filtertown">-->
          </div>
          <ul>
            <li v-for="(ville, index) in villesFiltre" :key="index">{{ville.nom}}</li>
          </ul>
        </div>

      </v-col>

      <v-col class="col-10">

        <div v-if="filterActive && villesFiltre.length === 1">
          <v-simple-table>
            <thead>
            <tr>
              <th>rues : {{villesFiltre[0].rues.length}}</th>
              <th>boutiques</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(rue, index) in villesFiltre[0].rues" :key="index">
              <td>{{rue.nom}} : {{rue.boutiques.length}} boutiques</td>
              <td>
                <ul>
                  <li v-for="(boutique, index) in rue.boutiques" :key="index">
                    {{boutique.nom}} : {{boutique.itemStock.length}} articles en stock, {{boutique.itemCommande.length}} sur commande
                  </li>
                </ul>
              </td>
            </tr>
            </tbody>
          </v-simple-table>
        </div>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import {mapState} from 'vuex'
export default {
  name: 'TownsView',
  data: () => ({
    filter: '',
    filterActive: false
  }),
  computed: {
    ...mapState(['villes']),
    villesFiltre() {
      if (this.filterActive){
        return this.villes.filter(v => v.nom.includes(this.filter))
      } else {
        return this.villes;
      }
    }
  }

}

</script>

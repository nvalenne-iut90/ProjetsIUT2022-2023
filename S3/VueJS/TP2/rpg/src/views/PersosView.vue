<template>
  <v-container>
    <v-row>
      <v-col class="col-2">
        <h1>Les personnages</h1>
        <v-select label="Sélectionner personnage" :items="selectItems" v-model="persoChoiced" ></v-select>
      </v-col>
      <v-col v-if="persoChoiced !== ''" class="col-10" >
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
                  {{emplacement.nom}}
                  <span v-if="emplacement.items.length !== 0">
                    [{{emplacement.items.length}}] : <span v-for="(item, indexItem) in emplacement.items" :key="indexItem">{{item.nom}}, </span>
                  </span>
                </li>
              </ul>
            </td>
          </tr>
          <tr>
            <td>{{perso_choisi.or}} or</td>
            <td>
              items achetés
              <span v-if="perso_choisi.itemsAchetes.length !== 0">
                [{{perso_choisi.itemsAchetes.length}}] :
                  <span v-for="(itemAchete, indexItemBuyed) in perso_choisi.itemsAchetes" :key="indexItemBuyed">{{itemAchete.nom}}</span>
              </span>
            </td>
          </tr>
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
    },
    selectItems(){
      let items = []
      this.persos.forEach(p => items.push(p.nom))
      return items
    }
  }
}
</script>

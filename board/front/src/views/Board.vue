<template>
  <v-container class="all">
    <h2 class="tableHeader pt-3" style="text-align:center; background-color:#8E24AA33; border-radius:16px 16px 0 0">Lali 게시판</h2>
    <v-data-table
      :headers="headers"
      :items="desserts"
      :search="search"
      sort-by="calories"
      class="table"
    >
      <template v-slot:top>
        <v-toolbar flat height="110px" width="100%">
          <v-row>
            <v-text-field
                color="#8E24AA"
                class="mt-6"
                v-model = "search"
                append-icon="mdi-magnify"
                label="검색할 단어를 입력해주세요"
                outlined
                background-color="#8E24AA22"
                >
            </v-text-field>
            <v-spacer></v-spacer>
            <v-btn to="/post" class="ma-5" fab color="#8E24AA44">
              <v-icon color="#8E24AA"> 
                mdi-card-plus 
              </v-icon>
            </v-btn>
          </v-row>
          <v-divider
            class="mx-4"
            inset
            vertical
          ></v-divider>
          
          <!-- 다이얼로그 내부 구역 -->
          <v-dialog
            v-model="dialog"
            max-width="500px"
          >
            <v-card>
              <!-- 수정하기 -->
              <v-card-title>
                <span class="text-h5">게시글 변경하기</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <!-- 제목 입력 -->
                    <v-col cols="12">
                      <v-text-field
                        v-model="editedItem.title"
                        label="제목"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12">
                      <v-text-field
                        v-model="editedItem.author"
                        label="작성자"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12">
                      <v-text-field
                        v-model="editedItem.content"
                        label="내용"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <!-- 다이얼로그 하단 취소 제출 버튼 -->
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="close"
                >
                  나가기
                </v-btn>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="save"
                >
                  저장하기
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <!-- 삭제에 대한 재확인 부 -->
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
                <v-card-title class="text-h5">정말 삭제하실건가요?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeDelete">아니요</v-btn>
                <v-btn color="blue darken-1" text @click="deleteItemConfirm">네</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
      <!-- 수정 삭제 기능 부착부 -->
      <template v-slot:item.actions="{ item }">
        <v-icon
          small
          class="mr-2"
          @click="editItem(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
          small
          @click="deleteItem(item)"
        >
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
  </v-container>
</template>
<script>
import axios from 'axios'
// import axios from 'axios'
import { fetchData } from '../utils/index'
// import axios from 'axios'

  export default {
    data: () => ({
      dialog: false,
      dialogDelete: false,
      editedIndex:0,
      
      headers: [
        { text: '게시글 번호', value: 'id',},
        { text: '제목', value: 'title' },
        { text: '작성자', value: 'author' },
        { text: '내용', value: 'content' },
        { text: '작성 일자', value: 'createdDate' },
        { text: '수정 | 삭제', value: 'actions', sortable: false },
      ],
      desserts: [],
      search:'',

      editedItem: {
        title: '',
        author: '',
        content: '', 
      },

      defaultItem: {
        id:[],
        title: [],
        author: [],
        content: [],
        createdDate:[]
      },
    }),

    watch: {
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

    created () {
      this.initialize()
    },

    methods: {
      async initialize () {
        const response = await fetchData();
        this.desserts= response.data;    
      },

      /**
       * 게시글 수정하기
       */
      editItem (item) {
        this.id= this.desserts.indexOf(item)
        this.editedItem = Object.assign({}, item)
        // const response = await axios.put(this.editedItem.id) 
        this.dialog = true
      },
      // 게시글 수정부
      async save () {
        Object.assign(this.desserts[this.id], this.editedItem)
        // console.log(this.editedItem.id);
        // console.log(this.desserts[this.id]);
        const response = await axios.put('http://localhost:8080/post/'+this.editedItem.id,
          {
            title: this.editedItem.title,
            author: this.editedItem.author,
            content: this.editedItem.content
          });
        console.log("< 수정된 정보 >");
        console.log(response);
        this.close()
      },
      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
        })
      },

      /**
       * 게시글 삭제하기
       */
      deleteItem (item) {
        this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },
      //게시글 삭제부
      async deleteItemConfirm () {
        this.desserts.splice(this.editedIndex, 1)
        const response = await axios.delete('http://localhost:8080/post/'+this.editedItem.id)
        console.log("< 삭제된 정보 >");
        console.log(response);
        this.closeDelete()
      },
      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
        })
      },
    }
  }
</script>

<style>
.tableHeader{
  height: 60px;
  width: 50%;
  margin: auto;  
}
.table{
  width: 50%;
  margin: auto;
}

</style>
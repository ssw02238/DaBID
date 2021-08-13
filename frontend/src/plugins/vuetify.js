import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

// import colors from 'vuetify/lib/util/colors'
import '@fortawesome/fontawesome-free/css/all.min.css'

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#292938',
                secondary: '#ebe2de',
                cardcolor: '#FDF4F4'
            }
        }
    },
    icons: {
        iconfont: 'fa',
        values: {
            live: 'fas fa-podcast',
            notice: 'fas fa-bullhorn',
            google: 'fab fa-google',
            pencil: 'far fa-edit'
        }
    },
});

import Engineering from "../views/board/Engineering.vue"
import IT from "../views/board/IT.vue"
import ManagementSupport from "../views/board/ManagementSupport.vue"
import Marketing from "../views/board/Marketing.vue"
import OverseasBusiness from "../views/board/OverseasBusiness.vue"
import ProductionMnufacturing from "../views/board/ProductionManufacturing.vue"
import SalesPlanning from "../views/board/SalesPlanning.vue"
import Specialty from "../views/board/Specialty.vue"
import BoardHome from "../views/board/BoardHome.vue"


export default[
    {
        path: '/board',
        component: BoardHome,
        name: 'boardHome',
        children: [
            {
                path: "engineering",
                component: Engineering,
                name: "engineering",
            },
            {
                path: "it",
                component: IT,
                name: "it",
            },
            {
                path: "managementSupport",
                component: ManagementSupport,
                name: "managementSupport",
            },
            {
                path: "marketing",
                component: Marketing,
                name: "marketing",
            },
            {
                path: "overseasBusiness",
                component: OverseasBusiness,
                name: "overseasBusiness",
            },
            {
                path: "productionManufacturing",
                component: ProductionMnufacturing,
                name: "productionManufacturing",
            },
            {
                path: "salesPlanning",
                component: SalesPlanning,
                name: "salesPlanning",
            },
            {
                path: "specialty",
                component: Specialty,
                name: "specialty",
            },
        ]
    }
]
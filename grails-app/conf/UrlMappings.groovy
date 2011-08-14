class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"home")
		"/index2"(view:"/index2")
		"500"(view:'/error')
	}
}

package org.raap.domain

class EthnicityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ethnicityInstanceList: Ethnicity.list(params), ethnicityInstanceTotal: Ethnicity.count()]
    }

    def create = {
        def ethnicityInstance = new Ethnicity()
        ethnicityInstance.properties = params
        return [ethnicityInstance: ethnicityInstance]
    }

    def save = {
        def ethnicityInstance = new Ethnicity(params)
        if (ethnicityInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'ethnicity.label', default: 'Ethnicity'), ethnicityInstance.id])}"
            redirect(action: "show", id: ethnicityInstance.id)
        }
        else {
            render(view: "create", model: [ethnicityInstance: ethnicityInstance])
        }
    }

    def show = {
        def ethnicityInstance = Ethnicity.get(params.id)
        if (!ethnicityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ethnicity.label', default: 'Ethnicity'), params.id])}"
            redirect(action: "list")
        }
        else {
            [ethnicityInstance: ethnicityInstance]
        }
    }

    def edit = {
        def ethnicityInstance = Ethnicity.get(params.id)
        if (!ethnicityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ethnicity.label', default: 'Ethnicity'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [ethnicityInstance: ethnicityInstance]
        }
    }

    def update = {
        def ethnicityInstance = Ethnicity.get(params.id)
        if (ethnicityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (ethnicityInstance.version > version) {
                    
                    ethnicityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ethnicity.label', default: 'Ethnicity')] as Object[], "Another user has updated this Ethnicity while you were editing")
                    render(view: "edit", model: [ethnicityInstance: ethnicityInstance])
                    return
                }
            }
            ethnicityInstance.properties = params
            if (!ethnicityInstance.hasErrors() && ethnicityInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'ethnicity.label', default: 'Ethnicity'), ethnicityInstance.id])}"
                redirect(action: "show", id: ethnicityInstance.id)
            }
            else {
                render(view: "edit", model: [ethnicityInstance: ethnicityInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ethnicity.label', default: 'Ethnicity'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def ethnicityInstance = Ethnicity.get(params.id)
        if (ethnicityInstance) {
            try {
                ethnicityInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'ethnicity.label', default: 'Ethnicity'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'ethnicity.label', default: 'Ethnicity'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ethnicity.label', default: 'Ethnicity'), params.id])}"
            redirect(action: "list")
        }
    }
}

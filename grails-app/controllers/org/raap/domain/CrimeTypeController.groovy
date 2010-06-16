package org.raap.domain

class CrimeTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [crimeTypeInstanceList: CrimeType.list(params), crimeTypeInstanceTotal: CrimeType.count()]
    }

    def create = {
        def crimeTypeInstance = new CrimeType()
        crimeTypeInstance.properties = params
        return [crimeTypeInstance: crimeTypeInstance]
    }

    def save = {
        def crimeTypeInstance = new CrimeType(params)
        if (crimeTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'crimeType.label', default: 'CrimeType'), crimeTypeInstance.id])}"
            redirect(action: "show", id: crimeTypeInstance.id)
        }
        else {
            render(view: "create", model: [crimeTypeInstance: crimeTypeInstance])
        }
    }

    def show = {
        def crimeTypeInstance = CrimeType.get(params.id)
        if (!crimeTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'crimeType.label', default: 'CrimeType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [crimeTypeInstance: crimeTypeInstance]
        }
    }

    def edit = {
        def crimeTypeInstance = CrimeType.get(params.id)
        if (!crimeTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'crimeType.label', default: 'CrimeType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [crimeTypeInstance: crimeTypeInstance]
        }
    }

    def update = {
        def crimeTypeInstance = CrimeType.get(params.id)
        if (crimeTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (crimeTypeInstance.version > version) {
                    
                    crimeTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'crimeType.label', default: 'CrimeType')] as Object[], "Another user has updated this CrimeType while you were editing")
                    render(view: "edit", model: [crimeTypeInstance: crimeTypeInstance])
                    return
                }
            }
            crimeTypeInstance.properties = params
            if (!crimeTypeInstance.hasErrors() && crimeTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'crimeType.label', default: 'CrimeType'), crimeTypeInstance.id])}"
                redirect(action: "show", id: crimeTypeInstance.id)
            }
            else {
                render(view: "edit", model: [crimeTypeInstance: crimeTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'crimeType.label', default: 'CrimeType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def crimeTypeInstance = CrimeType.get(params.id)
        if (crimeTypeInstance) {
            try {
                crimeTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'crimeType.label', default: 'CrimeType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'crimeType.label', default: 'CrimeType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'crimeType.label', default: 'CrimeType'), params.id])}"
            redirect(action: "list")
        }
    }
}

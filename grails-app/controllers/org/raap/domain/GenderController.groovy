package org.raap.domain

class GenderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [genderInstanceList: Gender.list(params), genderInstanceTotal: Gender.count()]
    }

    def create = {
        def genderInstance = new Gender()
        genderInstance.properties = params
        return [genderInstance: genderInstance]
    }

    def save = {
        def genderInstance = new Gender(params)
        if (genderInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gender.label', default: 'Gender'), genderInstance.id])}"
            redirect(action: "show", id: genderInstance.id)
        }
        else {
            render(view: "create", model: [genderInstance: genderInstance])
        }
    }

    def show = {
        def genderInstance = Gender.get(params.id)
        if (!genderInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gender.label', default: 'Gender'), params.id])}"
            redirect(action: "list")
        }
        else {
            [genderInstance: genderInstance]
        }
    }

    def edit = {
        def genderInstance = Gender.get(params.id)
        if (!genderInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gender.label', default: 'Gender'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [genderInstance: genderInstance]
        }
    }

    def update = {
        def genderInstance = Gender.get(params.id)
        if (genderInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (genderInstance.version > version) {
                    
                    genderInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gender.label', default: 'Gender')] as Object[], "Another user has updated this Gender while you were editing")
                    render(view: "edit", model: [genderInstance: genderInstance])
                    return
                }
            }
            genderInstance.properties = params
            if (!genderInstance.hasErrors() && genderInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gender.label', default: 'Gender'), genderInstance.id])}"
                redirect(action: "show", id: genderInstance.id)
            }
            else {
                render(view: "edit", model: [genderInstance: genderInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gender.label', default: 'Gender'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def genderInstance = Gender.get(params.id)
        if (genderInstance) {
            try {
                genderInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gender.label', default: 'Gender'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gender.label', default: 'Gender'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gender.label', default: 'Gender'), params.id])}"
            redirect(action: "list")
        }
    }
}

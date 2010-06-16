package org.raap.domain

class SexualityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sexualityInstanceList: Sexuality.list(params), sexualityInstanceTotal: Sexuality.count()]
    }

    def create = {
        def sexualityInstance = new Sexuality()
        sexualityInstance.properties = params
        return [sexualityInstance: sexualityInstance]
    }

    def save = {
        def sexualityInstance = new Sexuality(params)
        if (sexualityInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sexuality.label', default: 'Sexuality'), sexualityInstance.id])}"
            redirect(action: "show", id: sexualityInstance.id)
        }
        else {
            render(view: "create", model: [sexualityInstance: sexualityInstance])
        }
    }

    def show = {
        def sexualityInstance = Sexuality.get(params.id)
        if (!sexualityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sexuality.label', default: 'Sexuality'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sexualityInstance: sexualityInstance]
        }
    }

    def edit = {
        def sexualityInstance = Sexuality.get(params.id)
        if (!sexualityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sexuality.label', default: 'Sexuality'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sexualityInstance: sexualityInstance]
        }
    }

    def update = {
        def sexualityInstance = Sexuality.get(params.id)
        if (sexualityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sexualityInstance.version > version) {
                    
                    sexualityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sexuality.label', default: 'Sexuality')] as Object[], "Another user has updated this Sexuality while you were editing")
                    render(view: "edit", model: [sexualityInstance: sexualityInstance])
                    return
                }
            }
            sexualityInstance.properties = params
            if (!sexualityInstance.hasErrors() && sexualityInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sexuality.label', default: 'Sexuality'), sexualityInstance.id])}"
                redirect(action: "show", id: sexualityInstance.id)
            }
            else {
                render(view: "edit", model: [sexualityInstance: sexualityInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sexuality.label', default: 'Sexuality'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def sexualityInstance = Sexuality.get(params.id)
        if (sexualityInstance) {
            try {
                sexualityInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sexuality.label', default: 'Sexuality'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sexuality.label', default: 'Sexuality'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sexuality.label', default: 'Sexuality'), params.id])}"
            redirect(action: "list")
        }
    }
}

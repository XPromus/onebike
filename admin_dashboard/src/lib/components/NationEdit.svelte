<script lang="ts">
	import { invalidateAll } from "$app/navigation";
	import type { Nation, NationEditDto, PutNationDto } from "$lib/api/types/nations.d.ts";
	import Icon from "@iconify/svelte";
	import AddButton from "./Button.svelte";
	import AddDialog from "./AddDialog.svelte";
	import { onMount } from "svelte";

    let {
        selectedNation
    }: {
        selectedNation: Nation | null
    } = $props();

    let submitError = $state<string | null>(null);
    let nation = $derived<NationEditDto>({
        id: selectedNation != null ? selectedNation.id : null,
        longName: selectedNation != null ? selectedNation.longName : "",
        shortName: selectedNation != null ? selectedNation.shortName: "",
        flagEmoji: selectedNation != null ? selectedNation.flagEmoji: ""
    });
    let editMode: boolean = $derived(nation.id != null);

    const validateInput = (): boolean => {
        return nation.longName != "" && nation.shortName != "" && nation.flagEmoji != "";
    }

    const handleClear = () => {
        selectedNation = null;
    }

    const handleSubmit = async () => {
        submitError = null;

        const inputValid = validateInput();
        if (!inputValid) {
            submitError = "Input not valid. All fields need content.";
            return;
        }

        const putNationDto: Partial<PutNationDto> = {
			id: nation.id,
			longName: nation.longName,
			shortName: nation.shortName,
			flagEmoji: nation.flagEmoji
		}
        const response = await fetch(
            "/api/nations", {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(putNationDto)
            }
        );

        if (!response.ok) {
            submitError = `Update failed ${response.status}`;
            return;
        }

        await invalidateAll();
    }

    onMount(() => {
        handleClear();
    })
</script>

<div class="flex flex-col space-y-2">
    <div class="flex flex-row space-x-2">
        <AddButton 
            onclick={handleClear} 
            class={`rounded-md grow ${editMode ? "font-normal" : ""}`} 
            size="sm" 
            variant={editMode ? "panel" : "primary"}
        >
            <span>New Nation</span>
        </AddButton>
        <AddButton 
            class={`rounded-md grow ${editMode ? "" : "font-normal"}`} 
            size="sm" 
            variant={editMode ? "primary" : "panel"}
        >
            <span>Edit Mode</span>
        </AddButton>
    </div>
    <div class="flex flex-row space-x-2">
        <AddDialog bind:nationEdit={nation} />
        <AddButton onclick={handleClear} tooltip="Clear" class="rounded-md">
            <Icon icon="material-symbols:clear-all"/>
        </AddButton>
        <AddButton onclick={handleSubmit} tooltip={editMode ? "Update": "Create"} class="rounded-md">
            {#if editMode}
                <Icon icon="material-symbols:save-rounded"/>
            {:else}       
                <Icon icon="material-symbols:add-2-rounded"/>         
            {/if}
        </AddButton>
    </div>
</div>

{#if submitError}
    <p class="text-red-600">{submitError}</p>
{/if}

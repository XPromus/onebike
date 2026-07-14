<script lang="ts">
	import type { Nation } from "$lib/api/types/nations";
	import NationEdit from "$lib/components/NationEdit.svelte";
	import NationEntry from "$lib/components/NationEntry.svelte";
	import { type PageData } from "./$types";
	
    let { data }: { data: PageData } = $props();

    let selectedNation: Nation | null = $state<Nation | null>(null);
    const onNationSelect = (
        nation: Nation
    ) => {
        selectedNation = nation;
    }
</script>

<div class="space-y-2 flex overflow-hidden relative flex-col p-2 w-full h-full">
    <div class="flex flex-1 flex-col min-h-0 space-y-2">
        <div class="flex flex-row shrink-0 bg-mauve-0">
            <span class="font-bold text-center basis-1/3">Long Name</span>
            <span class="font-bold text-center basis-1/3">Short Name</span>
            <span class="font-bold text-center basis-1/3">Emoji</span>
        </div>
        <div class="flex overflow-y-scroll flex-col flex-1 min-h-0 space-y-1">
            {#each data.items as nation (nation.id) }
                <NationEntry onclick={() => { onNationSelect(nation) }} nation={nation}/>
            {/each}
        </div>
    </div>
    <NationEdit selectedNation={selectedNation} />
</div>
